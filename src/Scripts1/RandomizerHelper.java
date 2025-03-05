package Scripts1;

import java.util.Random;

public class RandomizerHelper {

    public int beforeEndEverythingTimer;
    int logoutWaitAfterTabSwitch;
    public int closeDialogueTimer;
    public int waitAfterFishClick;
    int randomizedSleepTimers;
    int randomCallAntiBanCamera;
    public int randomCallAntiBanTabs;
    public int randomCallAntiBanMouse;
    int beforeCameraMovementTimer, afterCameraMovementTimer;
    public int pauseBeforeTabs;
    public int pauseAfterTabs;
    public int randomMouseX;
    public int randomMouseY;
    public int mouseMovementTimerAfter;
    public int mouseMovementTimerBefore;
    public int longAFKTimer;
    public int AFKTimerFrequency;
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
        waitAfterFishClick = randSeed1.nextInt(2723) + 1448;
        closeDialogueTimer = randSeed1.nextInt(538) + 1012;
        beforeEndEverythingTimer = randSeed1.nextInt(5375) + 295;
        logoutWaitAfterTabSwitch = randSeed1.nextInt(1026) + 526;
        randomizedSleepTimers = randSeed1.nextInt(4560);
        randomDropVersion = randSeed1.nextInt(41) + 1;
        waifAfterEachDropClick = randSeed1.nextInt(1347) + 724;
        hoverTime = randSeed1.nextInt(860) + 202;

// AntiBan randomizers: tabs & camera
        afterCameraMovementTimer = randSeed1.nextInt(868) + 417;
        beforeCameraMovementTimer = randSeed1.nextInt(659) + 319;
        randomCallAntiBanCamera = randSeed1.nextInt(4038) + 101;
        randomCallAntiBanTabs = randSeed1.nextInt(1864);
        pauseBeforeTabs = randSeed1.nextInt(122) + 412;
        pauseAfterTabs = randSeed1.nextInt(2466) + 444;

// AntiBan randomizers: mouse movement
        randomCallAntiBanMouse = randSeed1.nextInt(3137) + 122;
        randomMouseX = randSeed1.nextInt(673) + 171;
        randomMouseY = randSeed1.nextInt(541) + 170;
        mouseMovementTimerBefore = randSeed1.nextInt(778) + 144;
        mouseMovementTimerAfter = randSeed1.nextInt(1008) + 155;

// Drop method randomizers
        dropSleepBeforeMouseMovement = randSeed1.nextInt(521) + 6;
        dropSleepAfterMouseMovement = randSeed1.nextInt(497) + 178;
        dropSleepBeforeClick = randSeed1.nextInt(805) + 194;
        dropSleepAfterShiftPress = randSeed1.nextInt(592) + 219;
        dropSleepBeforeShiftPress = randSeed1.nextInt(251) + 82;
        dropSleepAfterShiftRelease = randSeed1.nextInt(431);


    }
}



