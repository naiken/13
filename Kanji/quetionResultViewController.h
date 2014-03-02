//
//  quetionResultViewController.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/08.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "mainGameViewController.h"

//@class quetionResultViewController;
//
//@protocol quetionResultDelegate <NSObject>
//
//- (void)quetionResultChanged:(quetionResultViewController*)controller;
//
//@end

@interface quetionResultViewController : UIViewController{

    NSTimer* animationTimer;
    
}

//正解不正解の画像
@property(nonatomic,strong)IBOutlet UIImageView* resultImg;

//加算スコアラベル
@property(nonatomic,weak)IBOutlet UILabel* addScoreLabel;

//スコアラベル
@property(nonatomic,weak)IBOutlet UILabel* scoreLabel;

//次に遷移するボタン
@property (weak, nonatomic) IBOutlet UIButton *nextBtn;

//@property (nonatomic) id<quetionResultDelegate> delegate;

//加算スコア
@property(nonatomic)int addScore;

//スコア
@property(nonatomic)int score;

//アニメーション回数
@property(nonatomic)int animationCount;

//問題数
@property(nonatomic)int quetionCount;

//正解数
@property(nonatomic)int correctCount;

//難易度
@property(nonatomic)int difficulty;

//ボタンタップ遷移
-(IBAction)nextQuetionBtn:(id)sender;


@end
