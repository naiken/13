//
//  ViewController2.m
//  Windraw
//
//  Created by Hata Rie on 2013/12/18.
//  Copyright (c) 2013年 Rie Hata. All rights reserved.
//

#import "ViewController2.h"
#import <QuartzCore/QuartzCore.h>
#import <CoreGraphics/CoreGraphics.h>
#import <AssetsLibrary/AssetsLibrary.h>

@interface ViewController2 ()

{
    UIBezierPath *bezierPath;
    UIImage *lastDrawImage;
    UIImage *initDrawImage;
    NSMutableArray *undoStack;
    NSMutableArray *redoStack;
    CGPoint lastTouchPoint;
    BOOL firstMovedFlg;
}

@end

@implementation ViewController2

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"bgwhite" ofType:@"png"];
        initDrawImage = [UIImage imageWithContentsOfFile:imagePath];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    
    // 写真へのアクセスを開始する
    [self startUsePicture];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)startUsePicture
{
    // このアプリの写真への認証状態を取得する
    ALAuthorizationStatus status = [ALAssetsLibrary authorizationStatus];
    
    switch (status) {
        case ALAuthorizationStatusAuthorized: // 写真へのアクセスが許可されている
        case ALAuthorizationStatusNotDetermined: // 写真へのアクセスを許可するか選択されていない
        {
            __block NSMutableArray *groups = [NSMutableArray array];
            
            ALAssetsLibrary *library = [[ALAssetsLibrary alloc] init];
            [library enumerateGroupsWithTypes:ALAssetsGroupAll
                                   usingBlock:^(ALAssetsGroup *group, BOOL *stop) {
                                       if (group) {
                                           // グループ名を配列に追加
                                           [groups addObject:[group valueForProperty:ALAssetsGroupPropertyName]];
                                       } else {
                                           // 端末のアルバム名を一覧に表示する
                                           dispatch_async(dispatch_get_main_queue(), ^{
                                               UIAlertView *alertView = [[UIAlertView alloc]
                                                                         initWithTitle:@"確認"
                                                                         message:[NSString stringWithFormat:
                                                                                  @"保存先：この端末のアルバム\n%@",
                                                                                  [groups componentsJoinedByString:@"\n"]]
                                                                         delegate:nil
                                                                         cancelButtonTitle:@"OK"
                                                                         otherButtonTitles:nil];
                                               
                                               [alertView show];
                                           });
                                       }
                                       
                                   } failureBlock:^(NSError *error) {
                                       dispatch_async(dispatch_get_main_queue(), ^{
                                           UIAlertView *alertView = [[UIAlertView alloc]
                                                                     initWithTitle:@"エラー"
                                                                     message:@"このアプリでの写真へのアクセスを許可されなかったよ！"
                                                                     delegate:nil
                                                                     cancelButtonTitle:@"OK"
                                                                     otherButtonTitles:nil];
                                           [alertView show];
                                       });
                                   }];
        }
            break;
            
        case ALAuthorizationStatusRestricted: // 設定 > 一般 > 機能制限で利用が制限されている
        {
            UIAlertView *alertView = [[UIAlertView alloc]
                                      initWithTitle:@"エラー"
                                      message:@"設定 > 一般 > 機能制限で利用が制限されてるよ！"
                                      delegate:nil
                                      cancelButtonTitle:@"OK"
                                      otherButtonTitles:nil];
            [alertView show];
        }
            break;
            
        case ALAuthorizationStatusDenied: // 設定 > プライバシー > 写真で利用が制限されている
        {
            UIAlertView *alertView = [[UIAlertView alloc]
                                      initWithTitle:@"エラー"
                                      message:@"設定 > プライバシー > 写真で利用が制限されてるよ！"
                                      delegate:nil
                                      cancelButtonTitle:@"OK"
                                      otherButtonTitles:nil];
            [alertView show];
        }
            break;
            
        default:
            break;
    }
}




// 下部アイコン機能

- (IBAction)kumoru:(id)sender {
    [undoStack removeAllObjects];
    [redoStack removeAllObjects];
    NSString *imagePath = [[NSBundle mainBundle] pathForResource:@"bgwhite" ofType:@"png"];
    initDrawImage = [UIImage imageWithContentsOfFile:imagePath];
    lastDrawImage = initDrawImage;
    self.canvas.image = initDrawImage;
}

- (IBAction)hanten:(id)sender {
    CGAffineTransform revs = _canvas.transform;
    revs = CGAffineTransformScale( revs, -1.0, 1.0);
    _canvas.transform = revs;
}

- (IBAction)hozon:(id)sender {

    UIImage *image = [UIImage imageWithCGImage:UIGetScreenImage()];
    
    UIImageWriteToSavedPhotosAlbum([UIImage imageWithCGImage:UIGetScreenImage()], nil, nil, nil);
    
}


-(void)savePhotoPng:(UIImage*)orizinalSizeImage{
    NSData *imageData = UIImagePNGRepresentation(orizinalSizeImage);
    UIImage *pngimage = [UIImage imageWithData:imageData];
    
    UIImageWriteToSavedPhotosAlbum(pngimage, self, @selector(image:didFinishSavingWithError:contextInfo:), nil);
}

// 保存処理後のメッセージ
- (void)image:(UIImage *) image
didFinishSavingWithError:(NSError *) error
  contextInfo:(void *) contextInfo {
    NSString *message = @"保存しました。";
    if(error) message = @"保存に失敗しました。";
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@""
                                                    message:message
                                                   delegate:nil
                                          cancelButtonTitle:@"OK"
                                          otherButtonTitles:nil];
    [alert show];
}



// 描画機能

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    // タッチした座標を取得します。
    CGPoint currentPoint = [[touches anyObject] locationInView:self.canvas];
    
    // ボタン上の場合は処理を終了します。
    if (CGRectContainsPoint(self.kumoru.frame, currentPoint)
        || CGRectContainsPoint(self.hanten.frame, currentPoint)
        || CGRectContainsPoint(self.hozon.frame, currentPoint)
        || CGRectContainsPoint(self.cat.frame, currentPoint)
        || CGRectContainsPoint(self.right.frame, currentPoint)
        || CGRectContainsPoint(self.left.frame, currentPoint)
        || CGRectContainsPoint(self.top.frame, currentPoint)){
        return;
    }
    
    // 今回描画した画像を保持します。
    lastDrawImage = self.canvas.image;
    
    // パスを初期化します。
    bezierPath = [UIBezierPath bezierPath];
    bezierPath.lineCapStyle = kCGLineCapRound;
    bezierPath.lineWidth = 4.0;
    [bezierPath moveToPoint:currentPoint];
    firstMovedFlg = NO;
    
    // タッチした座標を保持します。
    lastTouchPoint = currentPoint;
}

- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
{
    // タッチ開始時にパスを初期化していない場合は処理を終了します。
    if (bezierPath == nil){
        return;
    }
    
    // タッチした座標を取得します。
    CGPoint currentPoint = [[touches anyObject] locationInView:self.canvas];
    
    // 最初の移動はスキップします。
    if (!firstMovedFlg){
        firstMovedFlg = YES;
        lastTouchPoint = currentPoint;
        return;
    }
    
    // 中点の座標を取得します。
    CGPoint middlePoint = CGPointMake((lastTouchPoint.x + currentPoint.x) / 2,
                                      (lastTouchPoint.y + currentPoint.y) / 2);
    
    
    // パスにポイントを追加します。
    [bezierPath addQuadCurveToPoint:middlePoint controlPoint:lastTouchPoint];
    
    // 線を描画します。
    [self drawLine:bezierPath];
    
    // タッチした座標を保持します。
    lastTouchPoint = currentPoint;
}


- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
{
    // タッチ開始時にパスを初期化していない場合は処理を終了します。
    if (bezierPath == nil){
        return;
    }
    
    // タッチした座標を取得します。
    CGPoint currentPoint = [[touches anyObject] locationInView:self.canvas];
    
    // パスにポイントを追加します。
    [bezierPath addQuadCurveToPoint:currentPoint controlPoint:lastTouchPoint];
    
    // 線を描画します。
    [self drawLine:bezierPath];
    
    // 今回描画した画像を保持します。
    lastDrawImage = self.canvas.image;
    
}

- (void)drawLine:(UIBezierPath*)path
{
    // 非表示の描画領域を生成します。
    UIGraphicsBeginImageContextWithOptions(self.canvas.frame.size, NO, 0.0);
    
    // 描画領域に、前回までに描画した画像を、描画します。
    [lastDrawImage drawAtPoint:CGPointZero];
    
    // 太さ
    [path setLineWidth:20];
    
    // 文字
    CGContextSetBlendMode(UIGraphicsGetCurrentContext(), kCGBlendModeClear);
    
    // 線を引きます。
    [path stroke];
    
    // 描画した画像をcanvasにセットして、画面に表示します。
    self.canvas.image = UIGraphicsGetImageFromCurrentImageContext();
    
    // 描画を終了します。
    UIGraphicsEndImageContext();
}



@end
