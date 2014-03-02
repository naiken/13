//
//  ViewController2.h
//  Windraw
//
//  Created by Hata Rie on 2013/12/18.
//  Copyright (c) 2013年 Rie Hata. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController2 : UIViewController

@property (weak, nonatomic) IBOutlet UIImageView *canvas;
@property (weak, nonatomic) IBOutlet UIButton *kumoru;
@property (weak, nonatomic) IBOutlet UIButton *hanten;
@property (weak, nonatomic) IBOutlet UIButton *hozon;
@property (weak, nonatomic) IBOutlet UIImageView *cat;
@property (weak, nonatomic) IBOutlet UIImageView *right;
@property (weak, nonatomic) IBOutlet UIImageView *left;
@property (weak, nonatomic) IBOutlet UIImageView *top;
    
- (IBAction)kumoru:(id)sender;
- (IBAction)hanten:(id)sender;
- (IBAction)hozon:(id)sender;


@end
