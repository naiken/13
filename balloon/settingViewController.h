//
//  settingViewController.h
//  balloon
//
//  Created by 吉永優 on 2014/01/15.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "mainViewController.h"
#import "appCCloud.h"

@interface settingViewController :  UIViewController


@property (weak, nonatomic) IBOutlet UITextField *sankaninzu;
//参加人数のテキストフィールド
@property (weak, nonatomic) IBOutlet UITextField *sensyutuninzu;
//選出人数のテキストフィールド
@property (nonatomic)int pCount;
//参加人数
@property (nonatomic)int sCount;
//選出人数
//（受け取り用）の参加人数と選出人数

@end


