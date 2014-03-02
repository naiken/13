//
//  quetionResultViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/08.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "quetionResultViewController.h"

@implementation quetionResultViewController

@synthesize resultImg;
@synthesize addScore;
@synthesize score;
@synthesize addScoreLabel;
@synthesize animationCount;
@synthesize scoreLabel;
@synthesize quetionCount;
@synthesize correctCount;
@synthesize nextBtn;
@synthesize difficulty;



- (IBAction)nextQuetionBtn:(id)sender {
    
    if (self.quetionCount == 10) {
        [self performSegueWithIdentifier:@"resultSegue" sender:self];
    }
    
    [self performSegueWithIdentifier:@"mainBack" sender:self];
//    if ([self.delegate respondsToSelector:@selector(quetionResultChanged:)]) {
//        [self.delegate quetionResultChanged:self];
//    }
//    
//    [self  dismissViewControllerAnimated:YES completion:nil];
//    //ストーリボードを取得
//    UIStoryboard* storyboard = [UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil];
//    
//    //ストーリーボード内のviewControllerを取得
//    mainGameViewController* controller = [storyboard instantiateViewControllerWithIdentifier:@"mainGameViewController"];
//    
//    controller.score = self.score;
//    controller.quetionCount = self.quetionCount;
//    controller.correctCount = self.correctCount;
//    
//    [self presentViewController:controller animated:YES completion:nil];
}

//最終結果とメインゲームに遷移するメソッド
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([segue.identifier isEqualToString:@"resultSegue"]) {
        
        resultViewController* result = segue.destinationViewController;
//        UIStoryboard* storyBoard = [UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil];
//        
//        resultViewController* controller = [storyBoard instantiateViewControllerWithIdentifier:@"resultViewController"];
        
        //問題数の初期化
        self.quetionCount = 0;
        
        //スコアの受け渡し
        result.score = self.score;
        //スコアの初期化
        self.score = 0;
        
        //正解数の受け渡し
        result.correctCount = self.correctCount;
        //正解数の初期化
        self.correctCount = 0;
        
        result.difficulty = self.difficulty;


    } else if([segue.identifier isEqualToString:@"mainBack"]) {
        
        mainGameViewController* controller = segue.destinationViewController;
        controller.score = self.score;
        controller.quetionCount = self.quetionCount;
        controller.correctCount = self.correctCount;
        controller.difficulty = self.difficulty;
    }
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    score += addScore;
    animationCount = 0;
    if (addScore == 0) {
        self.resultImg.image = [UIImage imageNamed:@"quetionFalse.png"];
    } else {
        self.resultImg.image = [UIImage imageNamed:@"quetionTrue.png"];
    }
    if (quetionCount == 10) {
        [self.nextBtn setTitle:@"RESULT" forState:UIControlStateNormal];
    }
    self.addScoreLabel.text = [NSString stringWithFormat:@"+ %d",addScore];
    
    
    animationTimer = [NSTimer scheduledTimerWithTimeInterval:0.02 target:self selector:@selector(animationLabel:) userInfo:nil repeats:YES];
    
    [animationTimer fire];

}


- (void)animationLabel:(NSTimer*)timer {
    
    animationCount += 1;
    
    float posY = self.addScoreLabel.center.y;
    float labelAlpha = self.addScoreLabel.alpha;
    if (animationCount >= 40) {
        labelAlpha -= 0.1;
        posY -= 2;
    } else {
        posY -= 2;
    }
    if (labelAlpha == 0.0) {
        [addScoreLabel removeFromSuperview];
        [animationTimer invalidate];
        
    }
    
    self.scoreLabel.text = [NSString stringWithFormat:@"%d",score];
    self.addScoreLabel.center = CGPointMake(self.addScoreLabel.center.x, posY);
    [self.addScoreLabel setAlpha:labelAlpha];
    
    
}




@end
