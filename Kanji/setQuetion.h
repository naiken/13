//
//  setQuetion.h
//  Kanji
//
//  Created by Doi Daihei on 2013/12/21.
//  Copyright (c) 2013年 Doi Daihei. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface setQuetion : NSObject {
}


//問題の画像格納、生成
+ (NSMutableArray*)quetionBox:(int)difficultyTag;

//問題の選択肢生成
+ (NSMutableDictionary*)selectBtnTitle:(int)difficultyTag quetionNumber:(int)number;

//問題の選択肢ランダムソート
+ (NSMutableArray*)randomSelect:(NSMutableDictionary*)selectStringDictionary;



extern NSString* const keasyTrue_string[30];
extern NSString* const keasyFalse_string[30][3];
extern NSString* const kmediumTrue_string[30];
extern NSString* const kmediumFalse_string[30][3];
extern NSString* const khardTrue_string[30];
extern NSString* const khardFalse_string[30][3];


@end
