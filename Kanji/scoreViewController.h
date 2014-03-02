//
//  scoreViewController.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/01.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "scoreEstablish.h"
#import "appCCloud.h"

@interface scoreViewController : UIViewController {
    NSNumber* score;
}

- (IBAction)backToClickButton:(id)sender;

//ラベルの生成
- (UILabel*)makeScoreLabel;

@end
