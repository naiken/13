//
//  mainGameViewController.m
//  Kanji
//
//  Created by Doi Daihei on 2013/12/08.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import "mainGameViewController.h"
#import "quetionResultViewController.h"

#define TRUE_FLAG 1
#define FALSE_FLAG 0

@implementation mainGameViewController

@synthesize timeLabel;
@synthesize score;
@synthesize quetionCount;
@synthesize correctCount;
@synthesize difficulty;
@synthesize soundHuseikaiID;
@synthesize soundHuseikaiURL;
@synthesize soundSeikaiID;
@synthesize soundSeikaiURL;
@synthesize selectButton1;
@synthesize selectButton2;
@synthesize selectButton3;
@synthesize selectButton4;

- (void)viewDidLoad {
    [super viewDidLoad];
    
    srand((unsigned int)time(NULL));
    randomValue = rand() % 30;
    currentTime = 5.00;
    
    CFBundleRef mainBundle = CFBundleGetMainBundle();
    
    soundSeikaiURL = CFBundleCopyResourceURL(mainBundle, CFSTR("seikai"), CFSTR("mp3"), NULL);
    AudioServicesCreateSystemSoundID(soundSeikaiURL, &soundSeikaiID);
    CFRelease(soundSeikaiURL);
    
    soundHuseikaiURL = CFBundleCopyResourceURL(mainBundle, CFSTR("huseikai"), CFSTR("mp3"), NULL);
    AudioServicesCreateSystemSoundID(soundHuseikaiURL, &soundHuseikaiID);
    CFRelease(soundHuseikaiURL);
    
    quetions = [setQuetion quetionBox:self.difficulty];
    quetionImage = [UIImage imageNamed:[quetions objectAtIndex:randomValue]];
    quetionView = [self quetionImageSet:quetionImage];
    
    selectTitle = [setQuetion selectBtnTitle:self.difficulty quetionNumber:randomValue];
    
    randomSortTitle = [setQuetion randomSelect:selectTitle];
    
    NSString* compareStr = @"TRUE";
    
    self.timeLabel.text = [NSString stringWithFormat:@"%1.2f",currentTime];
    for (int i = 0; i<4; i++) {
        NSString* str = [randomSortTitle objectAtIndex:i];
        
        if (i == 0){
            
            [self.selectButton1 setTitle:[selectTitle objectForKey:str] forState:UIControlStateNormal];
            
            
            
            if ([str isEqualToString:compareStr]) {
                [self.selectButton1 setTag:TRUE_FLAG];
            } else {
                [self.selectButton1 setTag:FALSE_FLAG];
            }
            
                [self.selectButton1 addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];

            
//            [self.view addSubview:button1];
        } else if (i == 1){
//            UIButton* button2 = [self makeBtn:CGRectMake(180, 354, 120, 82)];
            [self.selectButton2 setTitle:[selectTitle objectForKey:str] forState:UIControlStateNormal];
            
            if ([str isEqualToString:compareStr]) {
                [self.selectButton2 setTag:TRUE_FLAG];
            } else {
                [self.selectButton2 setTag:FALSE_FLAG];
            }
                [self.selectButton2 addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];

//            [self.view addSubview:button2];
        } else if (i == 2){
//            UIButton* button3 = [self makeBtn:CGRectMake(20, 444, 120, 82)];
            
            [self.selectButton3 setTitle:[selectTitle objectForKey:str] forState:UIControlStateNormal];
            
            if ([str isEqualToString:compareStr]) {
                [self.selectButton3 setTag:TRUE_FLAG];
            } else {
                [self.selectButton3 setTag:FALSE_FLAG];
            }
                [self.selectButton3 addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];

//            [self.view addSubview:self.selectButton3];
        } else if (i == 3){
//            UIButton* self.selecetButton4 = [self makeBtn:CGRectMake(180, 444, 120, 82)];
            [self.selectButton4 setTitle:[selectTitle objectForKey:str] forState:UIControlStateNormal];
            
            if ([str isEqualToString:compareStr]) {
                [self.selectButton4 setTag:TRUE_FLAG];
            } else {
                [self.selectButton4 setTag:FALSE_FLAG];
            }
            [self.selectButton4 addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];

//            [self.view addSubview:self.selecetButton4];
        }
        
    }
    
    timer = [NSTimer scheduledTimerWithTimeInterval:(0.02) target:self selector:@selector(timeLabelCount:) userInfo:nil repeats:YES];
    [timer fire];

}



- (void)viewDidUnload {
    [super viewDidUnload];
    [timer invalidate];
    
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    if ([segue.identifier isEqualToString:@"quetionTrueSegue"]) {
        AudioServicesPlaySystemSound(soundSeikaiID);

        quetionResultViewController* controller = segue.destinationViewController;
//        controller.delegate = self;
        controller.addScore = currentTime * 100;
        controller.score = self.score;
        self.quetionCount += 1;
        self.correctCount += 1;
        
//        quetionView = NULL;
//        quetionImage = NULL;
        controller.correctCount = self.correctCount;
        controller.quetionCount = self.quetionCount;
        controller.difficulty = self.difficulty;

    } else if([segue.identifier isEqualToString:@"quetionFalseSegue"]) {
        AudioServicesPlaySystemSound(soundHuseikaiID);

        quetionResultViewController* controller = segue.destinationViewController;
//        controller.delegate = self;
        controller.addScore = 0;
        controller.score = self.score;
        self.quetionCount += 1;
        
//        quetionView = NULL;
//        quetionImage = NULL;
        controller.quetionCount = self.quetionCount;
        controller.correctCount = self.correctCount;
        controller.difficulty = self.difficulty;
    }
}


//- (UIButton*)makeBtn:(CGRect)rect {
//    UIButton* button = [UIButton buttonWithType:UIButtonTypeRoundedRect];
//    //[button setTag:tag];
//    [button setFrame:rect];
//    //[button setTitle:title forState:UIControlStateNormal];
//    button.backgroundColor = [UIColor blueColor];
//    [button addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];
//    
//    
//    return button;
//}

- (void)timeLabelCount:(NSTimer*)onTimer {
    
//    NSLog(@"%.2f",currentTime);
    if (currentTime > 0.02) {
        currentTime = currentTime - 0.02;
//        NSLog(@"%.2f",currentTime);

        self.timeLabel.text = [NSString stringWithFormat:@"%1.2f",currentTime];
    } else {
        [timer invalidate];
        [self performSegueWithIdentifier:@"quetionFalseSegue" sender:self];
    }
    
    
    
}

- (UIImageView*)quetionImageSet:(UIImage*)quetionImageset {
    UIImageView* image = [[UIImageView alloc] init];
    [image setImage:quetionImageset];
    CGRect screenRect = [[UIScreen mainScreen] bounds];
    if (screenRect.size.height == 480) {
        [image setFrame:CGRectMake(40, 60, 240, 220)];
    } else{
        [image setFrame:CGRectMake(40, 89, 245, 245)];
    }
    [self.view addSubview:image];
    
    return image;
}

////問題に正解した際のメソッド
//- (void)trueTransition {
//    UIStoryboard* storyBoard = [UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil];
//    quetionResultViewController* controller = [storyBoard instantiateViewControllerWithIdentifier:@"quetionResultViewController"];
//    
//
//    controller.addScore = currentTime * 100;
//    controller.score = self.score;
//    self.quetionCount += 1;
//    self.correctCount += 1;
//    controller.correctCount = self.correctCount;
//    controller.quetionCount = self.quetionCount;
//
//    
//    [self presentViewController:controller animated:YES completion:nil];
//}

////問題に不正解した際のメソッド
//- (void)falseTransition {
//
//    UIStoryboard* storyBoard = [UIStoryboard storyboardWithName:@"Main_iPhone" bundle:nil];
//    quetionResultViewController* controller = [storyBoard instantiateViewControllerWithIdentifier:@"quetionResultViewController"];
//    
//
//    controller.addScore = 0;
//    controller.score = self.score;
//    self.quetionCount += 1;
//    controller.quetionCount = self.quetionCount;
//    controller.correctCount = self.correctCount;
//    
//    [self presentViewController:controller animated:YES completion:nil];
//    
//}



-  (void)selectBtnClick:(UIButton*)btn{
    
    if (btn.tag == TRUE_FLAG) {
        [timer invalidate];
//        AudioServicesPlaySystemSound(soundSeikaiID);
        [self performSegueWithIdentifier:@"quetionTrueSegue" sender:self];
    } else if (btn.tag == FALSE_FLAG){
        [timer invalidate];
//        AudioServicesPlaySystemSound(soundHuseikaiID);
        [self performSegueWithIdentifier:@"quetionFalseSegue" sender:self];
    }
}

//- (void)quetionResultChanged:(quetionResultViewController*)controller{
//    self.score = controller.score;
//    self.quetionCount = controller.quetionCount;
//    self.correctCount = controller.correctCount;
//}

//- (NSArray*)quetionBox:(int)difficultyTag {
//    
//    NSArray* qBox = [NSArray array];
//    if (difficultyTag == 0) {
//        for (int i = 0; i < 30; i++) {
//            
//            
//            UIImage* qImg = [UIImage imageNamed:
//                             [NSString stringWithFormat:@"%d.png",i]];
//            qBox = [NSArray arrayWithObject:qImg];
//        }
//    } else if (difficultyTag == 1) {
//        for (int i = 100; i < 115; i++) {
//            UIImage* qImg = [UIImage imageNamed:
//                             [NSString stringWithFormat:@"%d.png",i]];
//            qBox = [NSArray arrayWithObject:qImg];
//        }
//    } else if (difficultyTag == 2) {
//        for (int i = 200; i < 217; i++) {
//            UIImage* qImg = [UIImage imageNamed:
//                             [NSString stringWithFormat:@"%d.png",i]];
//            qBox = [NSArray arrayWithObject:qImg];
//        }
//    }
//    
//    return qBox;
//    
//}




@end
