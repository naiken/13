//
//  tutorialViewController.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/01.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "appCCloud.h"

@interface tutorialViewController : UIViewController{
    UITextView* caption;
}
@property (readwrite,weak) IBOutlet UIButton *quetionLayer;

- (IBAction)backToClickButton:(id)sender;

//- (IBAction)pauseBtnClick:(id)sender;

- (IBAction)timeLabelClick:(id)sender;

- (IBAction)quetionLayerClick:(id)sender;

- (IBAction)selectBtnClick:(id)sender;

- (IBAction)correctBtnClick:(id)sender;

@end
