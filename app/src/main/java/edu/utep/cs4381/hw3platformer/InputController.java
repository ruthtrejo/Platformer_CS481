package edu.utep.cs4381.hw3platformer;


import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;

public class InputController {

    Rect left;
    Rect right;
    Rect jump;
    Rect shoot;
    Rect pause;

    InputController(int screenWidth, int screenHeight) {

        //Configure the player buttons
        int buttonWidth = screenWidth / 8;
        int buttonHeight = screenHeight / 7;
        int buttonPadding = screenWidth / 80;

        left = new Rect(buttonPadding,
                screenHeight - buttonHeight - buttonPadding,
                buttonWidth,
                screenHeight - buttonPadding);

        right = new Rect(buttonWidth + buttonPadding,
                screenHeight - buttonHeight - buttonPadding,
                buttonWidth + buttonPadding + buttonWidth,
                screenHeight - buttonPadding);

        jump = new Rect(screenWidth - buttonWidth - buttonPadding,
                screenHeight - buttonHeight - buttonPadding - buttonHeight - buttonPadding,
                screenWidth - buttonPadding,
                screenHeight - buttonPadding - buttonHeight - buttonPadding);

        shoot = new Rect(screenWidth - buttonWidth - buttonPadding,
                screenHeight - buttonHeight - buttonPadding,
                screenWidth - buttonPadding,
                screenHeight - buttonPadding);

        pause = new Rect(screenWidth - buttonPadding - buttonWidth,
                buttonPadding,
                screenWidth - buttonPadding,
                buttonPadding + buttonHeight);



    }

    public ArrayList getButtons(){
        //create an array of buttons for the draw method
        ArrayList<Rect> currentButtonList = new ArrayList<>();
        currentButtonList.add(left);
        currentButtonList.add(right);
        currentButtonList.add(jump);
        currentButtonList.add(shoot);
        currentButtonList.add(pause);
        return  currentButtonList;
    }


    public void handleInput(MotionEvent motionEvent,LevelManager lm, SoundManager sound, Viewport vp){
        int pointerCount = motionEvent.getPointerCount();

        for (int i = 0; i < pointerCount; i++) {

            int x = (int) motionEvent.getX(i);
            int y = (int) motionEvent.getY(i);

            if(lm.isPlaying()) {

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        if (right.contains(x, y)) {
                            lm.player.setPressingRight(true);
                            lm.player.setPressingLeft(false);
                        } else if (left.contains(x, y)) {
                            lm.player.setPressingLeft(true);
                            lm.player.setPressingRight(false);
                        } else if (jump.contains(x, y)) {
                            lm.player.startJump(sound);
                        } else if (shoot.contains(x, y)) {
                            // handle shooting here
                        } else if (pause.contains(x, y)) {
                            lm.switchPlayingStatus();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        if (right.contains(x, y)) {
                            lm.player.setPressingRight(false);
                        } else if (left.contains(x, y)) {
                            lm.player.setPressingLeft(false);
                        }
                        break;

                }
            }//end LM.ISPLAYING

            else {// Not playing
                //Move the viewport around to explore the map
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        if (right.contains(x, y)) {
                            vp.moveViewportRight(lm.mapWidth);
                            //Log.w("right:", "DOWN" );
                        } else if (left.contains(x, y)) {
                            vp.moveViewportLeft();
                            //Log.w("left:", "DOWN" );
                        } else if (jump.contains(x, y)) {
                            vp.moveViewportUp();
                            //Log.w("jump:", "DOWN" );/
                        } else if (shoot.contains(x, y)) {
                            vp.moveViewportDown(lm.mapHeight);
                            //Log.w("shoot:", "DOWN" );/
                        } else if (pause.contains(x, y)) {
                            lm.switchPlayingStatus();
                            //Log.w("pause:", "DOWN" );
                        }

                        break;
                }
            }
        }
    }
}
