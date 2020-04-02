import * as functions from 'firebase-functions';
import * as admin from 'firebase-admin';
//import firebase = require( "firebase/app" );

// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript
//
let numberOfTouchingUsers = 0;
let g_everyoneTouching = false;
// make it dynamic (rooms creation)
const roomSize = 2;
let touchingUsersIds: string[] = [];
const TOUCHING_ACTION_UP = "UP"
const TOUCHING_ACTION_DOWN = "DOWN"

export const helloWorld = functions.https.onRequest((request, response) => {
    console.log("I SUCCEEDED TO DEPLOY FUNCTION");
    response.send("Hello from Firebase!");
});

export const userTouching = functions.database.ref('rooms/{roomId}/users/{userId}/touch_action/')
    .onWrite(async (change, context) => {
      console.log('User ID', context.params.userId);
      touchingUsersIds.push(context.params.userId);
      console.log('Room ID', context.params.roomId);
      let touchAction = change.after.val();
      console.log('Touch Action', touchAction);

      switch(touchAction) {
        case TOUCHING_ACTION_UP:
            if (numberOfTouchingUsers > 0) {
                numberOfTouchingUsers = numberOfTouchingUsers - 1;
                stopToRaffle(context.params.roomId);
                console.log('Number of user left to Touch', roomSize - numberOfTouchingUsers);
            }
            break;
        case TOUCHING_ACTION_DOWN:
            if (numberOfTouchingUsers < roomSize) {
                numberOfTouchingUsers = numberOfTouchingUsers + 1;
                if (numberOfTouchingUsers === roomSize) {
                    g_everyoneTouching = true;
                    // setIsEveryoneTouching(context.params.roomId, true);
                    executeAsync(function() {
                      startRaffle(context.params.roomId).then().catch();
                    });
                }
                console.log('Number of user left to Touch', roomSize - numberOfTouchingUsers);
            }
            break;
        default:
            break;
      }

      return null;
});

function stopToRaffle(roomId: string) {
    g_everyoneTouching = false;
//    setIsEveryoneTouching(roomId, false);
}

function isEveryoneTouching(roomId: string) {
    return g_everyoneTouching;
//    return functions.database.ref('/rooms/' + roomId).getValue();
}

//function setIsEveryoneTouching(roomId, everyoneTouching) {
//    const pushRef = functions.database.ref('/rooms/' + roomId).push();
//    let isEveryoneTouchingObject = {
//        "isEveryoneTouching": everyoneTouching
//    };
//    pushRef.set(isEveryoneTouchingObject);
//}

async function startRaffle(roomId: string) {
    let timePassedMs = 0;
    const intervalMs = 500;
    const TIME_BEFORE_RAFFLE = 3000;
    while(isEveryoneTouching(roomId) && timePassedMs !== TIME_BEFORE_RAFFLE) {
      await delay(intervalMs);
      timePassedMs += intervalMs;
    }

    if (TIME_BEFORE_RAFFLE === timePassedMs) {
        const ref = admin.database.child('/rooms/' + roomId);
        let randomUser = randomNumber(0, roomSize);
        let raffleResult = {
            "raffleResult": touchingUsersIds[randomUser]
        };
        ref.set(raffleResult);
    }
}

function delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function executeAsync(func: function) {
    setTimeout(func, 0);
}

function randomNumber(min: number, max: number) {
  return Math.random() * (max - min) + min;
}