//
//  ViewController.m
//  Windraw
//
//  Created by Hata Rie on 2013/12/18.
//  Copyright (c) 2013年 Rie Hata. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.

    [appCCloud setupAppCWithMediaKey:@"7ad377d8465675b000bca171420db2bd28ee4f66" option:APPC_CLOUD_AD];
    
    appCSimpleView* apcSimpleView = [[appCSimpleView alloc] initWithViewController:self vertical:appCVerticalBottom];
    
    [self.view addSubview:apcSimpleView];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)toSecond:(id)sender
{
    [self performSegueWithIdentifier:@"toSecond" sender:self];
}

@end
