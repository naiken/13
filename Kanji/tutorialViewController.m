//
//  tutorialViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/01.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "tutorialViewController.h"

@implementation tutorialViewController
@synthesize quetionLayer;

- (void)viewDidLoad {
    CGRect screenRect = [[UIScreen mainScreen] bounds];
    
    if (screenRect.size.height == 480) {
        [super viewDidLoad];
    }else{
    [super viewDidLoad];
    
    [appCCloud setupAppCWithMediaKey:@"6af8e68bba4fa52e1e1674c2eedb8b9c2518a36f" option:APPC_CLOUD_AD];
    
    appCMarqueeView* view = [[appCMarqueeView alloc] initWithViewController:self vertical:appCVerticalBottom];
    [self.view addSubview:view];
    }
    
}



- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event {
    [self.view willRemoveSubview:caption];
    
}



- (UITextView*)captionLabel:(CGRect)rect textLabel:(NSString*)textLabel {
    
    UITextView* cLabel = [[UITextView alloc] initWithFrame:rect];
    
    [cLabel setText:textLabel];
    cLabel.editable = NO;
    [cLabel setCanCancelContentTouches:YES];
    [cLabel setTextAlignment:NSTextAlignmentLeft];
    [cLabel setTextColor:[UIColor blackColor]];
    
    
    return cLabel;
    
}


- (IBAction)backToClickButton:(id)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}

//- (IBAction)pauseBtnClick:(id)sender {
//    
//    caption = nil;
//    
//    NSString* captionLabel = @"このポーズボタンを押すと、ゲームを一時中断し、ゲームをやめる事やリスタートできるよ。";
//    caption = [self captionLabel:CGRectMake(100, 100, 150, 70) textLabel:captionLabel];
//    
//    [self.view addSubview:caption];
//}

- (IBAction)timeLabelClick:(id)sender {
    
    caption = nil;
    
    NSString* captionLabel = @"各問題に制限時間があり、その回答した時間により、SCOREが変動するよ";
    
    caption = [self captionLabel:CGRectMake(100, 100, 150, 70) textLabel:captionLabel];
    
    [self.view addSubview:caption];
}

- (IBAction)quetionLayerClick:(id)sender {
    caption = nil;
    
    NSString* captionLabel = @"画面中央に漢字を模した絵が出てくるよ";
    
    caption = [self captionLabel:CGRectMake(100, 100, 150, 70) textLabel:captionLabel];
    
    [self.view addSubview:caption];
}

- (IBAction)selectBtnClick:(id)sender {
    caption = nil;
    
    NSString* captionLabel = @"問題に対して回答にあたる「読み方」を四つから選択してね";
    
    caption = [self captionLabel:CGRectMake(100, 100, 150, 70) textLabel:captionLabel];
    
    [self.view addSubview:caption];
}

- (IBAction)correctBtnClick:(id)sender {
    caption = nil;
    
    NSString* captionLabel = @"正解だよ！こんな流れで遊んでね！良いスコアは出るかな？";
    
    caption = [self captionLabel:CGRectMake(100, 100, 150, 70) textLabel:captionLabel];
    
    [self.view addSubview:caption];
}

@end
