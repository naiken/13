//
//  selectballoon.h
//  balloon
//
//  Created by 吉永優 on 2014/01/24.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface selectballoon : NSObject


+ (NSArray*)randomSelect:(NSMutableArray*)selectStringDictionary pCount:(int)personcount;

@end
