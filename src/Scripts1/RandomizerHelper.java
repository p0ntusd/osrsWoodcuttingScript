package Scripts1;

import java.util.Random;

public class RandomizerHelper {

    public int beforeEndEverythingTimer;
    int logoutWaitAfterTabSwitch;
    public int closeDialogueTimer;
    public int waitAfterFishClick;
    int randomizedSleepTimers;
    int randomCallAntiBanCamera, randomCallAntiBanTabs, randomCallAntiBanMouse;
    int beforeCameraMovementTimer, afterCameraMovementTimer;
    int pauseBeforeTabs,pauseAfterTabs;
    int randomMouseX,randomMouseY;
    int mouseMovementTimerAfter,mouseMovementTimerBefore;
    int longAFKTimer, AFKTimerFrequency;
    public int randomDropVersion;
    int waifAfterEachDropClick;
    int dropSleepAfterShiftRelease;
    int dropSleepBeforeMouseMovement, dropSleepAfterMouseMovement, dropSleepBeforeClick;
    int dropSleepAfterShiftPress;
    public int dropSleepBeforeShiftPress;
    public int hoverTime;

    public RandomizerHelper() {

        Random randSeed1 = new Random();
        Random randSeed2 = new Random();
        Random randSeed3 = new Random();

        //other main randomizers
        waitAfterFishClick = randSeed1.nextInt(2897) + 1578;
        closeDialogueTimer = randSeed3.nextInt(721)+ 985;
        beforeEndEverythingTimer = randSeed1.nextInt(5170) + 497;
        logoutWaitAfterTabSwitch = randSeed2.nextInt(877 )+589;
        randomizedSleepTimers = randSeed3.nextInt(4710);
        randomDropVersion = randSeed1.nextInt(30);
        waifAfterEachDropClick = randSeed2.nextInt(1170) + 477;
        hoverTime = randSeed3.nextInt(677);

        //AntiBan randomizers: tabs & camera
        afterCameraMovementTimer = randSeed1.nextInt(831)+432;
        beforeCameraMovementTimer = randSeed3.nextInt(412) + 237;
        randomCallAntiBanCamera = randSeed2.nextInt(4155);
        randomCallAntiBanTabs = randSeed3.nextInt(1997);
        pauseBeforeTabs = randSeed1.nextInt(344)+ 355;
        pauseAfterTabs = randSeed2. nextInt(2312)+ 210;

        //AntiBan randomizers: mouse movement
        randomCallAntiBanMouse =randSeed1.nextInt(3210);
        randomMouseX = randSeed3.nextInt(763);
        randomMouseY = randSeed1.nextInt(502);
        mouseMovementTimerBefore = randSeed1.nextInt(777);
        mouseMovementTimerAfter = randSeed2.nextInt(1023);

        //drop method randomizers
        dropSleepBeforeMouseMovement = randSeed1.nextInt(541);
        dropSleepAfterMouseMovement= randSeed2.nextInt(669);
        dropSleepBeforeClick = randSeed3.nextInt(671);
        dropSleepAfterShiftPress = randSeed1.nextInt(351);
        dropSleepBeforeShiftPress = randSeed2.nextInt(421);
        dropSleepAfterShiftRelease = randSeed3.nextInt(599);

    }
}



