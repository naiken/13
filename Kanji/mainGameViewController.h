//
//  mainGameViewController.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/08.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "quetionResultViewController.h"
#import "resultViewController.h"
#import "setQuetion.h"
#include <AudioToolbox/AudioToolbox.h>

@interface mainGameViewController : UIViewController {
    
    //問題を選択するランダム変数
    int randomValue;
    
    //制限時間
    float currentTime;
    
    
    //毎問題に制限時間のセット
    NSTimer* timer;
    
    
    //画像の表示
    UIImage* quetionImage;
    
    UIImageView* quetionView;

    
    //画像の格納配列
    NSMutableArray* quetions;
    
    //選択肢の格納
    NSMutableDictionary* selectTitle;
    
    //選択肢のランダムキー
    NSMutableArray* randomSortTitle;
    
    //正解効果音のURL
    CFURLRef soundSeikaiURL;
    SystemSoundID soundSeikaiID;
    
    //不正解効果音のURL
    CFURLRef soundHuseikaiURL;
    SystemSoundID soundHuseikaiID;
}

//制限時間を示すラベル
@property (nonatomic, weak)IBOutlet UILabel* timeLabel;

//難易度選択を受け取る
@property (nonatomic)int difficulty;


//スコアを保持する
@property (nonatomic)int score;


//問題数の保持
@property(nonatomic)int quetionCount;

//正解数の保持
@property(nonatomic)int correctCount;

@property(readwrite)CFURLRef soundSeikaiURL;
@property(readonly)SystemSoundID soundSeikaiID;

@property(readwrite)CFURLRef soundHuseikaiURL;
@property(readonly)SystemSoundID soundHuseikaiID;

@property (weak, nonatomic) IBOutlet UIButton *selectButton1;
@property (weak, nonatomic) IBOutlet UIButton *selectButton2;
@property (weak, nonatomic) IBOutlet UIButton *selectButton3;
@property (weak, nonatomic) IBOutlet UIButton *selectButton4;

- (void)timeLabelCount:(NSTimer*)onTimer;




@end
