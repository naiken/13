//
//  resultViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/13.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "resultViewController.h"

@implementation resultViewController


@synthesize score;
@synthesize resultScore;
@synthesize correctCount;
@synthesize scoreLabel;
@synthesize finalScore;
@synthesize correctLabel;
@synthesize bonusLabel;
@synthesize difficulty;


- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.scoreLabel.text = [NSString stringWithFormat:@"%d",self.score];
    self.correctLabel.text = [NSString stringWithFormat:@"%d",self.correctCount];
//    storyboard = [UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil];
    
    timer = [NSTimer scheduledTimerWithTimeInterval:3.0 target:self selector:@selector(finalScoreOccur) userInfo:nil repeats:NO];
    
    [timer fire];
}

- (void)finalScoreOccur {
    if (self.correctCount == 10) {
        self.bonusLabel.text = [NSString stringWithFormat:@"Perfect Bonus!!"];
        self.resultScore = score + 1000;
        self.finalScore.text = [NSString stringWithFormat:@"%d",self.resultScore];
    }//ボーナスは検討中
    else {
        self.bonusLabel.text = [NSString stringWithFormat:@"Bonusなし"];
        self.resultScore = score;
        self.finalScore.text = [NSString stringWithFormat:@"%d",self.resultScore];
    }
    
    scoreEstablish* se = [[scoreEstablish alloc] init];
    [se scoreMemorise:self.resultScore difficulty:self.difficulty];
    
}

- (IBAction)titlePush:(id)sender{
//    
//    unchiViewController* controller = [storyboard instantiateViewControllerWithIdentifier:@"unchiViewController"];
//    
//    [self presentViewController:controller animated:YES completion:nil];
    
    [self performSegueWithIdentifier:@"titleBackSegue" sender:self];
}

- (IBAction)scorePush:(id)sender{
//    scoreViewController* controller = [storyboard instantiateViewControllerWithIdentifier:@"scoreViewController"];
//    
//    [self presentViewController:controller animated:YES completion:nil];
    
    [self performSegueWithIdentifier:@"scoreBackSegue" sender:self];
}

- (IBAction)restartPush:(id)sender{
//    mainGameViewController* controller = [storyboard instantiateViewControllerWithIdentifier:@"mainGameViewController"];
//    
//    controller.score = 0;
//    controller.quetionCount = 0;
//    controller.correctCount = 0;
//    
//    [self presentViewController:controller animated:YES completion:nil];
    
    [self performSegueWithIdentifier:@"restartSegue" sender:self];
}
- (IBAction)appCCloudListView:(id)sender {
    
    [appCCloud setupAppCWithMediaKey:@"6af8e68bba4fa52e1e1674c2eedb8b9c2518a36f" option:APPC_CLOUD_AD];
    
    __block appCCutinView* cutIn = [[appCCutinView alloc] initWithViewController:self closeBlock:^(id sender_){
        [cutIn removeFromSuperview];
    }];
    
    [self.view addSubview:cutIn];
                                    
    
    
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if ([segue.identifier isEqualToString:@"titleBackSegue"]) {
        
    } else if ([segue.identifier isEqualToString:@"scoreBackSegue"]){
        
    } else if ([segue.identifier isEqualToString:@"restartSegue"]){
        
        mainGameViewController* controller = segue.destinationViewController;
        controller.score = 0;
        controller.quetionCount = 0;
        controller.correctCount = 0;
        controller.difficulty = self.difficulty;
    }
}




@end
