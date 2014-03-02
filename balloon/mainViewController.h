//
//  mainViewController.h
//  balloon
//
//  Created by 吉永優 on 2014/01/15.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "settingViewController.h"
#import "selectballoon.h"
#import "appCCloud.h"

@interface mainViewController : UIViewController{
    NSTimer* balloonAnimationtimer;
    NSArray* balloonbox;
    NSMutableArray* randomSortballoon;
    NSMutableArray* randombox;
    NSArray* bnumber;
}

@property (weak, nonatomic) IBOutlet UIButton *start;
@property (weak, nonatomic) IBOutlet UIImageView *yellowballoon;
@property (weak, nonatomic) IBOutlet UIImageView *pinkballoon;
@property (weak, nonatomic) IBOutlet UIImageView *redballoon;
@property (weak, nonatomic) IBOutlet UIImageView *orangeballoon;
@property (weak, nonatomic) IBOutlet UIImageView *greenballoon;
@property (weak, nonatomic) IBOutlet UIImageView *purpleballoon;
@property (weak, nonatomic) IBOutlet UIButton *modoru;
@property (nonatomic)int pCount;
@property(nonatomic)int sCount;
//@property(nonatomic)float balloonAlpha;



@end

