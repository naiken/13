//
//  scoreEstablish.h
//  Kanji
//
//  Created by Doi Daihei on 2014/01/08.
//  Copyright (c) 2014年 Doi Daihei. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface scoreEstablish : NSObject

//スコアを記憶する
- (void)scoreMemorise:(int)score difficulty:(int)difficulty;

//スコアを取得する
+ (NSNumber*)getScore:(int)number difficulty:(int)difficulty;

- (void)memoryScore;

@end
