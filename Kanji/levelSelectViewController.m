//
//  levelSelectViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/01.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "levelSelectViewController.h"

@implementation levelSelectViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    
    [appCCloud setupAppCWithMediaKey:@"6af8e68bba4fa52e1e1674c2eedb8b9c2518a36f" option:APPC_CLOUD_AD];
    
    appCSimpleView* apcSimpleView = [[appCSimpleView alloc] initWithViewController:self vertical:appCVerticalBottom];
    
    [self.view addSubview:apcSimpleView];

    
}
- (IBAction)backToClickButton:(id)sender {
//    [self dismissViewControllerAnimated:YES completion:nil];
    [self performSegueWithIdentifier:@"backToTitle" sender:self];
}


- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    mainGameViewController* controller = segue.destinationViewController;
    
    if ([segue.identifier isEqualToString:@"easySegue"]) {
        
        controller.quetionCount = 0;
        controller.score = 0;
        controller.correctCount = 0;
        controller.difficulty = 0;
    } else if ([segue.identifier isEqualToString:@"mediumSegue"]){
        
        controller.quetionCount = 0;
        controller.score = 0;
        controller.correctCount = 0;
        controller.difficulty = 1;
    } else if ([segue.identifier isEqualToString:@"hardSegue"]){
        
        controller.quetionCount = 0;
        controller.score = 0;
        controller.correctCount = 0;
        controller.difficulty = 2;
    } else if ([segue.identifier isEqualToString:@"backToTitle"]){
    }
}

- (IBAction)easyTap:(id)sender {
    [self performSegueWithIdentifier:@"easySegue" sender:self];
}

- (IBAction)mediumTap:(id)sender {
    [self performSegueWithIdentifier:@"mediumSegue" sender:self];
}

- (IBAction)hardTap:(id)sender {
    [self performSegueWithIdentifier:@"hardSegue" sender:self];
}


@end
