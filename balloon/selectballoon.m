//
//  selectballoon.m
//  balloon
//
//  Created by 吉永優 on 2014/01/24.
//  Copyright (c) 2014年 吉永優. All rights reserved.
//

#import "selectballoon.h"

@implementation selectballoon

+ (NSArray*)randomSelect:(NSMutableArray*)selectStringDictionary pCount:(int)personcount {
    
    srand(time(NULL));
    for (int i = 0; i<100; i++) {
        int src = rand() % personcount;
        int dst = src;
        while (dst == src) {
            dst = rand() % personcount;
        }
        [selectStringDictionary exchangeObjectAtIndex:src withObjectAtIndex:dst];
    }
    NSArray* strArray = [selectStringDictionary mutableCopy];
    return strArray;
}

@end
