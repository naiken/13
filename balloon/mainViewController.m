//
//  mainViewController.m
//  balloon
//
//  Created by 吉永優 on 2014/01/15.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import "mainViewController.h"
#import "settingViewController.h"

#define BALLOON_WIDTH 120
#define BALLOON_HEIGHT 110

@implementation mainViewController
@synthesize pCount;
@synthesize sCount;
@synthesize yellowballoon;
@synthesize pinkballoon;
@synthesize redballoon;
@synthesize orangeballoon;
@synthesize greenballoon;
@synthesize purpleballoon;
@synthesize start;
@synthesize modoru;

-(void)viewDidLoad{
    [super viewDidLoad];
    balloonbox = [NSArray arrayWithObjects:self.yellowballoon,self.pinkballoon,self.orangeballoon,self.redballoon,self.greenballoon,self.purpleballoon,nil];
    for (int i = self.pCount; i < 6; i++) {
        [[balloonbox objectAtIndex:i] setHidden:YES];
    }
    NSArray* balloonnumber = @[@0,@1,@2,@3,@4,@5];
    NSMutableArray* MAnumber = [balloonnumber mutableCopy];
    bnumber = [selectballoon randomSelect:MAnumber pCount:self.pCount];
    
    [appCCloud setupAppCWithMediaKey:@"38e91d9de31102ea0cf5f09294a581c763d5e643" option:APPC_CLOUD_AD];
    
    appCSimpleView* apcSimpleView = [[appCSimpleView alloc] initWithViewController:self vertical:appCVerticalBottom];
    
    [self.view addSubview:apcSimpleView];
    
}

- (IBAction)haretustart:(id)sender {
    [self.start removeFromSuperview];
    balloonAnimationtimer = [NSTimer scheduledTimerWithTimeInterval:0.2f target:self selector:@selector(haretu) userInfo:Nil repeats:YES];
    [balloonAnimationtimer fire];
}

//文字列になった風船をランダムに並べて、前から選出人数分取り出す
- (void)haretu{

    for (int i = 0; i < (self.pCount - self.sCount); i++) {
        int number;
        NSNumber* x = [bnumber objectAtIndex:i];
        number = [x intValue];
        UIImageView* haretuballoon = [balloonbox objectAtIndex:number];
        UIImage *img = [UIImage imageNamed:@"pop4.png"];
        haretuballoon.image = img;
        [haretuballoon setContentMode:UIViewContentModeScaleToFill];
         }
}

- (IBAction)settingnimodoru:(id)sender {
    [self performSegueWithIdentifier:@"modoru" sender:self];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"modoru"]) {
        
    }
}

@end

