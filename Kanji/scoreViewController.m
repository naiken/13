//
//  scoreViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/01.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "scoreViewController.h"

#define LABEL_WIDTH 135
#define LABEL_HEIGHT 25
#define LABEL_DY 30

@implementation scoreViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    
    CGRect screenRect = [[UIScreen mainScreen] bounds];
    for (int i = 0; i < 3; i++) {
        if (i == 0) {
            int x = 20;
            int y = 125;
            for (int j = 0; j < 5; j++) {
                UILabel* scoreLabel = [self makeScoreLabel];
                [scoreLabel setFrame:CGRectMake(x, y + LABEL_DY*j, LABEL_WIDTH, LABEL_HEIGHT)];
                score = [scoreEstablish getScore:j difficulty:i];
                [scoreLabel setText:[NSString stringWithFormat:@"%@",score]];
                
                [self.view addSubview:scoreLabel];
            }

        } else if (i == 1) {
            int x = 167;
            int y = 125;
            for (int j = 0; j < 5; j++) {
                UILabel* scoreLabel = [self makeScoreLabel];
                [scoreLabel setFrame:CGRectMake(x, y + LABEL_DY*j, LABEL_WIDTH, LABEL_HEIGHT)];
                score = [scoreEstablish getScore:j difficulty:i];
                [scoreLabel setText:[NSString stringWithFormat:@"%@",score]];
                
                [self.view addSubview:scoreLabel];
            }
            
        } else if (i == 2) {
            
            int x = 93;
            int y;
            if (screenRect.size.height == 480) {
                y = 309;
            }else{
                y = 360;
            }
            for (int j = 0; j < 5; j++) {
                UILabel* scoreLabel = [self makeScoreLabel];

                [scoreLabel setFrame:CGRectMake(x, y + LABEL_DY*j, LABEL_WIDTH, LABEL_HEIGHT)];
                score = [scoreEstablish getScore:j difficulty:i];
                [scoreLabel setText:[NSString stringWithFormat:@"%@",score]];
                
                [self.view addSubview:scoreLabel];
            }
        }
    }
    
    
    if (screenRect.size.height == 480) {
        [appCCloud setupAppCWithMediaKey:@"6af8e68bba4fa52e1e1674c2eedb8b9c2518a36f" option:APPC_CLOUD_AD];
        
        appCMarqueeView* view = [[appCMarqueeView alloc] initWithViewController:self vertical:appCVerticalBottom];
        [self.view addSubview:view];
    }else{
        [appCCloud setupAppCWithMediaKey:@"6af8e68bba4fa52e1e1674c2eedb8b9c2518a36f" option:APPC_CLOUD_AD];
    
        appCSimpleView* apcSimpleView = [[appCSimpleView alloc] initWithViewController:self vertical:appCVerticalBottom];
    
        [self.view addSubview:apcSimpleView];
    }
}




- (IBAction)backToClickButton:(id)sender {
//    [self dismissViewControllerAnimated:YES completion:nil];
    [self performSegueWithIdentifier:@"backToMain" sender:self];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if ([segue.identifier isEqualToString:@"backToMain"]) {
        
    }
}



- (UILabel*)makeScoreLabel {
    
    UILabel* sLabel = [[UILabel alloc] init];
    UIFont* font = [UIFont fontWithName:@"HeitiSC" size:17];
    
    [sLabel setFont:font];
    [sLabel setTextAlignment:NSTextAlignmentRight];
    return sLabel;
    
}

@end
