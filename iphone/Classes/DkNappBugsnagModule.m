/**
 * NappBugsnag
 *
 * Created by Mads Møller
 * Copyright (c) 2015 Napp. All rights reserved.
 */

#import "DkNappBugsnagModule.h"
#import "TiBase.h"
#import "TiHost.h"
#import "TiUtils.h"


@implementation DkNappBugsnagModule

#pragma mark Internal

// this is generated for your module, please do not change it
-(id)moduleGUID
{
	return @"5f48ff4f-53f8-4b33-88e7-e41f04a9394e";
}

// this is generated for your module, please do not change it
-(NSString*)moduleId
{
	return @"dk.napp.bugsnag";
}

#pragma mark Lifecycle

-(void)startup
{
	// this method is called when the module is first loaded
	// you *must* call the superclass
	[super startup];
}

-(void)shutdown:(id)sender
{
	// you *must* call the superclass
	[super shutdown:sender];
}


#pragma Public APIs


-(id)startBugsnagWithConfiguration:(id)args
{
    ENSURE_SINGLE_ARG(args, NSDictionary);
    
    // BUGSNAG
    BugsnagConfiguration *configuration = [[BugsnagConfiguration alloc] init];
    configuration.apiKey = [TiUtils stringValue:@"apikey" properties:args];
    
    // notifyReleaseStages
    if([args objectForKey:@"notifyReleaseStages"] != nil){
        configuration.notifyReleaseStages = [NSArray arrayWithArray:[args objectForKey:@"notifyReleaseStages"]];
    } else {
        // default - only production reporting
        configuration.notifyReleaseStages = [NSArray arrayWithObjects:@"production", nil];
    }
    
    // lets go
    [Bugsnag startBugsnagWithConfiguration:configuration];
}


-(void)addAttribute:(id)args
{
    ENSURE_SINGLE_ARG(args, NSDictionary);
    
    NSString *attribute;
    NSString *tabName;
    
    ENSURE_ARG_FOR_KEY(attribute, args, @"attribute", NSString);
    ENSURE_ARG_FOR_KEY(tabName, args, @"tabName", NSString);
    
    [Bugsnag addAttribute:attribute withValue:[args objectForKey:@"data"] toTabWithName:tabName];
}

-(void)clearTabWithName:(id)value
{
    ENSURE_SINGLE_ARG(value, NSString);
    [Bugsnag clearTabWithName:value];
}

-(void)notify:(id)args
{
    ENSURE_SINGLE_ARG(args, NSDictionary);
    
    NSString *name;
    NSString *reason;
    
    ENSURE_ARG_FOR_KEY(name, args, @"name", NSString);
    ENSURE_ARG_FOR_KEY(reason, args, @"reason", NSString);
    
    [Bugsnag notify:[NSException exceptionWithName:name reason:reason userInfo:nil]];
}

-(void)setContext:(id)value
{
    ENSURE_SINGLE_ARG(value, NSString);
    [[Bugsnag configuration] setContext:value];
}

@end
