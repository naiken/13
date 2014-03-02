//
//  resultViewController.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/13.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "mainGameViewController.h"
#import "unchiViewController.h"
#import "scoreViewController.h"
#import "mainGameViewController.h"
#import "scoreEstablish.h"
#import "appCCloud.h"

@interface resultViewController : UIViewController {
//    UIStoryboard* storyboard;
    

    

    
    NSTimer* timer;

}

//最終結果のスコア
@property (nonatomic)int resultScore;
@property(nonatomic,weak) IBOutlet UILabel *finalScore;
@property(nonatomic,weak)IBOutlet UILabel *correctLabel;
@property(nonatomic,weak)IBOutlet UILabel *scoreLabel;
@property(nonatomic,weak)IBOutlet UILabel *bonusLabel;
//スコア
@property (nonatomic)int score;

//正解数
@property (nonatomic)int correctCount;

@property (nonatomic)int difficulty;




//タイトルボタン遷移メソッド
- (IBAction)titlePush:(id)sender;

//スコアボタン遷移メソッド
- (IBAction)scorePush:(id)sender;

//リスタートボタン遷移メソッド
- (IBAction)restartPush:(id)sender;

@end
