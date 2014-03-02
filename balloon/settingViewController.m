//
//  settingViewController.m
//  balloon
//
//  Created by 吉永優 on 2014/01/15.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import "settingViewController.h"

#define ALERT_TAG 100

@interface settingViewController ()

@end

@implementation settingViewController
@synthesize sankaninzu;
@synthesize sensyutuninzu;
@synthesize pCount;
@synthesize sCount;


//- (IBAction)kettei:(id)sender {
//[sankaninzu endEditing:YES];
//}
- (IBAction)sankaninzu:(id)sender {
    [self.sankaninzu resignFirstResponder];
    
}
- (IBAction)sensyutuninzu:(id)sender {
    [self.sensyutuninzu resignFirstResponder];
    
}
- (IBAction)onTappedSankaninzu:(id)sender {
    // ラベルを消す
    UILabel* _eLabel = (UILabel*)[self.view viewWithTag:ALERT_TAG];
    [_eLabel setText:@""];
}

- (IBAction)kettei:(id)sender {
    self.pCount =  [self.sankaninzu.text integerValue];
    self.sCount = [self.sensyutuninzu.text integerValue];
    
    // pCountとsCountをint型に変換
    int i_pCount = (int)self.pCount;
    int i_sCount = (int)self.sCount;
    
    // 文字を入力した場合
    if(i_pCount == 0 || i_sCount == 0) {
        UILabel* eLabel = (UILabel*)[self.view viewWithTag:ALERT_TAG];
        [eLabel setText:@"数字を入力してください"];
        
    // 6より大きい数字
    }else if(self.pCount >6){
        UILabel* eLabel = (UILabel*)[self.view viewWithTag:ALERT_TAG];
        [eLabel setText:@"6までの数字を入力してください"];

    }
    // pCountがsCountよりも小さい場合
    else if(i_sCount >= i_pCount){
        UILabel* eLabel = (UILabel*)[self.view viewWithTag:ALERT_TAG];
        [eLabel setText:@"もう一度人数を入力してください"];

    // 入力した値が負の値の場合
    }else if(self.pCount <0 || self.sCount<0){
        UILabel* eLabel = (UILabel*)[self.view viewWithTag:ALERT_TAG];
        [eLabel setText:@"1より大きい数字を入力してください"];
    }

    // 正常系
    else{
        [self performSegueWithIdentifier:@"selecttomain" sender:self];
    }
    
}



- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.sankaninzu.placeholder = @"数字を入力";
    self.sensyutuninzu.placeholder = @"数字を入力";
    
    // 警告用のラベルを追加
    UILabel* eLabel = [[UILabel alloc]init];
    [eLabel setFrame:CGRectMake(10, 180, 300, 21)];
    [eLabel setText:@""];
    [eLabel setTextAlignment:NSTextAlignmentCenter];
    eLabel.tag = ALERT_TAG;
    [self.view addSubview:eLabel];
    
    
    //	int random_nummber;
    //    random_nummber = rand() % personCount;
    //    //0～6の数値をランダムに取得
    //    srand(time(NULL));//被らない数値を渡して初期化
    [appCCloud setupAppCWithMediaKey:@"38e91d9de31102ea0cf5f09294a581c763d5e643" option:APPC_CLOUD_AD];
    
    appCSimpleView* apcSimpleView = [[appCSimpleView alloc] initWithViewController:self vertical:appCVerticalBottom];
    
    [self.view addSubview:apcSimpleView];
    
    
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"selecttomain"]) {
        mainViewController* balloon = segue.destinationViewController;
        //遷移先のコントローラーを取得
//        self.pCount =  [self.sankaninzu.text integerValue];
//        self.sCount = [self.sankaninzu.text integerValue];
        //テキストボックス内の数字を取得
        balloon.pCount = self.pCount;
        balloon.sCount = self.sCount;
        //遷移先のコントローラーに値を渡す
    }
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
