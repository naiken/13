//
//  unchiViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/11/24.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "unchiViewController.h"

@interface unchiViewController ()

@end

@implementation unchiViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    scoreEstablish* se = [[scoreEstablish alloc]init];
    
    [se memoryScore];
    	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
