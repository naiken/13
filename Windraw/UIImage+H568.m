//
//  UIImage+H568.m
//  Windraw
//
//  Created by Hata Rie on 2014/02/08.
//  Copyright (c) 2014年 Rie Hata. All rights reserved.
//

#import "UIImage+H568.h"
#include "objc/runtime.h"

@interface UIImage+H568 ()

@end

@implementation UIImage (H568)

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

+ (void)load
{
    if ((UI__USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone)
        ([UIScreen mainScreen).bounds.size.height > 480.0f)) {
        method_exchangeImplementations(class_getClassMethod, self,
                                        class_getClassMethod(<#__unsafe_unretained Class cls#>, <#SEL name#>)), <#Method m2#>)
    }
}

@end
