//
//  scoreEstablish.m
//  Kanji
//
//  Created by Doi Daihei on 2014/01/08.
//  Copyright (c) 2014年 Doi Daihei. All rights reserved.
//

#import "scoreEstablish.h"

@implementation scoreEstablish

- (void)scoreMemorise:(int)score difficulty:(int)difficulty {
    NSUserDefaults* defaluts = [NSUserDefaults standardUserDefaults];
    NSString* scoreStr = [NSString stringWithFormat:@"score_Array_%d",difficulty];
    
    NSArray* scoreArray = [defaluts arrayForKey:scoreStr];
    
    if (scoreArray) {
        NSMutableArray* changeScore = [scoreArray mutableCopy];
        [changeScore addObject:[NSNumber numberWithInt:score]];
        NSArray* sortedArray = [changeScore sortedArrayUsingComparator:^NSComparisonResult(NSNumber* a, NSNumber* b){
            return b.intValue - a.intValue;
        }];
        
        changeScore = [sortedArray mutableCopy];
        [changeScore removeLastObject];
        NSArray* memorizeScore = (NSArray*)changeScore;
        
        [defaluts setObject:memorizeScore forKey:scoreStr];
        
        BOOL success = [defaluts synchronize];
        if (success) {
            NSLog(@"%@",@"データの保存に成功しました");
        }
        
    } else {
        NSArray* defaultDic = @[@0,@0,@0,@0,@0];
        [defaluts setObject:defaultDic forKey:scoreStr];
        
        NSMutableArray* changeScore = (NSMutableArray*)defaultDic;
        [changeScore addObject:[NSNumber numberWithInt:score]];
        
        NSArray* sortedArray = [changeScore sortedArrayUsingComparator:^NSComparisonResult(NSNumber* a, NSNumber* b){
            return b.intValue - a.intValue;
        }];
        
        changeScore = [sortedArray mutableCopy];
        [changeScore removeLastObject];
        NSArray* memorizeScore = (NSArray*)changeScore;
        
        [defaluts setObject:memorizeScore forKey:scoreStr];
        
        BOOL success = [defaluts synchronize];
        if (success) {
            NSLog(@"%@",@"初回のデータの保存に成功しました");
        }
    }
    
}

+ (NSNumber*)getScore:(int)number difficulty:(int)difficulty {
    NSUserDefaults* defaults = [NSUserDefaults standardUserDefaults];
    NSString* scoreStr = [NSString stringWithFormat:@"score_Array_%d",difficulty];
    
    NSArray* scoreArray = [defaults arrayForKey:scoreStr];
    NSNumber* getScore;
    if (scoreArray) {
        
        getScore = [scoreArray objectAtIndex:number];
        return getScore;
    } else {
        NSArray* defaultDic = @[@0,@0,@0,@0,@0];
        [defaults setObject:defaultDic forKey:scoreStr];
        
        scoreArray = [defaults objectForKey:scoreStr];
        getScore = [scoreArray objectAtIndex:number];
        return getScore;
    }
    
}

- (void)memoryScore {
    NSUserDefaults* defaults = [NSUserDefaults standardUserDefaults];
    NSArray* defaultDic = @[@0,@0,@0,@0,@0];
    
    for (int i = 0; i<3; i++) {
        NSString* scoreStr = [NSString stringWithFormat:@"score_Array_%d",i];
        NSArray* scoreArray = [defaults arrayForKey:scoreStr];
        if (scoreArray) {
            return;
        } else{
            
            [defaults setObject:defaultDic forKey:scoreStr];
        }

    }
}


@end
