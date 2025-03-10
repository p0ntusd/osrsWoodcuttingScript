/**
 * First ever runescape script.
 * Made for the OSBot api following
 * Will Bosch's guide.
 *
 * @Author  p0ntus
 * @date    05/03 -25 (eu)
 */

import org.osbot.rs07.api.GraphicObjects;
import org.osbot.rs07.api.Objects;
import org.osbot.rs07.api.Tabs;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import Scripts1.RandomizerHelper;

import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.stream.IntStream;

@ScriptManifest(info = "Wood and Drop", logo = "", version = 1.0, author = "p0ntus", name = "Woodcutter")

public class Main extends Script {

    private static final int MAX_IDLE_TIMER = 200;
    private static final int AXE_ID = 1351;
    private int idleCounter = 0;
    RandomizerHelper rand;

    static int[] LOGS_ID = new int[8];
    static int[] INVENTORY_TO_KEEP = new int[8];
    static int[] WOOD_CUT_IDS = new int[8];
    static double WOODCUTTING_SPEED = 1;
    static int[] TREE_IDS = new int[8];

    @Override
    public void onStart() throws InterruptedException {
        GUI gui = new GUI();
        getBot().getScriptExecutor().pause();

        /**
         * ISTÄLLEt FÖR ATT FIXA GUI
         */
        TREE_IDS[0] = 1278;
        WOOD_CUT_IDS[0] = 1511;
        INVENTORY_TO_KEEP[0] = 1351;

        while (gui.isActive() || gui.isVisible()) {
            sleep(1427);
            log("SLEEPING @onStart()");
        }

        if (!gui.isVisible() || !gui.isActive())
            getBot().getScriptExecutor().resume();

        if (INVENTORY_TO_KEEP == null || WOOD_CUT_IDS == null) {
            log("INVENTORY OR WOOD_CUT_ID IS NULL.");
            stop();
        }
    }

    @Override
    public int onLoop() throws InterruptedException {
        log("Idle Counter = " + idleCounter + "of " + MAX_IDLE_TIMER);
        idleCounter++;

        rand = new RandomizerHelper();

        closeDialog();

        cutWood();

        callAllAntiBanMethods();

        sleepRandomTime();

        stopExcessiveIdling();

        return 500;
    }

    public void stopExcessiveIdling() throws InterruptedException {
        if(idleCounter > MAX_IDLE_TIMER) {
            log("idleTimeOutExit()");
            endEverything();
        }
    }


    private void sleepRandomTime() throws InterruptedException {
        sleep((long) ((long)rand.randomizedSleepTimers * WOODCUTTING_SPEED));
    }

    public void cutWood() throws InterruptedException {
        log(TREE_IDS[0]);
        boolean cutSuccess = false;
        Entity tree = getObjects().closest("Tree");
        log(tree);

        if (isReadyToCut() && tree != null && !getInventory().isFull()) {
            sleep(rand.hoverTime);
            cutSuccess = tree.interact("Chop down");
            log("Tries to chop...");
        }
        sleep(rand.waitAfterFishClick);

        if(cutSuccess)
            idleCounter = 0;

        if(cutSuccess) {
            log("Chop success!");
        } else {
            log("Chop failed.");
        }
    }

    void dropWood() throws InterruptedException {
        log("dropWood()");

        if (rand.randomDropVersion < 3)
            getInventory().dropAllExcept(INVENTORY_TO_KEEP);
        else if (rand.randomDropVersion == 3)
            while(getInventory().dropAllExcept(WOOD_CUT_IDS)) {
                getInventory().getItem(WOOD_CUT_IDS).interact("Drop");
                sleep(new Random().nextInt(544) + 676);
            }
        else if(rand.randomDropVersion == 4)
            getInventory().dropAll(WOOD_CUT_IDS);
        else
            customDrop();
    }

    private void customDrop() throws InterruptedException {
        log("customDrop()");

        sleep(rand.dropSleepBeforeShiftPress);
        getKeyboard().pressKey(KeyEvent.VK_SHIFT);

        for (int x = 0; x < 5; x++) {
            int xItemID = getInventory().getItemInSlot(x).getId();
            if(IntStream.of(INVENTORY_TO_KEEP).noneMatch(y -> y == xItemID) && getInventory().getItemInSlot(x) != null) {
                if(getInventory().isItemSelected()) {
                    getInventory().deselectItem();
                    woodDropSpeedSleep();
                }
                woodDropSpeedSleep();
                getMouse().click(false);
                sleep(new Random().nextInt(246)+ 230);
            }
        }
    }

    private void woodDropSpeedSleep() throws InterruptedException {
        if(WOODCUTTING_SPEED < 1)
            sleep(new Random().nextInt(183));
        else if(WOODCUTTING_SPEED > 1)
            sleep(new Random().nextInt(540));
        else
            sleep(new Random().nextInt(399));
    }

    private boolean isReadyToCut() throws InterruptedException {
        log("isReadyToCut() = ");

        if (isInventoryFull())
            dropWood();

        if (myPlayer().isAnimating() || myPlayer().isMoving() && myPlayer().getInteracting() != null) {
            log(false);
            return false;
        }

        if(!getInventory().contains(AXE_ID) && !getEquipment().contains(AXE_ID)) {
            log(false + " no axe.");
            endEverything();
        }

        log(true);
        return true;
    }

    private void endEverything() throws InterruptedException {
        log("endEverything()");

        sleep(rand.beforeEndEverythingTimer);
        stop();
        sleep(new Random().nextInt(22307) + 8620);
        //System.exit(2)
    }

    private boolean isInventoryFull() {
        boolean temp = getInventory().isFull();
        log("isInventoryFull() = " + temp);
        return temp;
    }

    private void closeDialog() throws InterruptedException {
        log("Closing Dialog");

        if (getWidgets().getWidgetContainingText("Click here to continue") != null) {
            sleep((long)(rand.closeDialogueTimer * WOODCUTTING_SPEED));
            getKeyboard().typeKey(' ');
        }
        idleCounter++;
    }

    /**
     * -------------------- Anti-Ban Methods --------------------
     *
     * Stolen.
     */

    public void callAllAntiBanMethods() throws InterruptedException {
        log("callAllAntiBanMethods()");
        if(rand.randomCallAntiBanTabs == 10)
            antiBanTabs();
        if(rand.randomCallAntiBanMouse == 10)
            antiBanMouse();
        if (rand.randomCallAntiBanMouse < 3)
            getMouse().moveOutsideScreen();
        if(rand.AFKTimerFrequency == 1)
            antiBanLongAFK();

    }

    /*prevent bans. It will randomly but rarely switch to the SKILL tab.*/
    public void antiBanTabs() throws InterruptedException {
        log("antiBanTabs()");

        Tabs myTabs = getTabs();
        sleep(rand.pauseBeforeTabs);
        if(myTabs.getOpen() != Tab.SKILLS) {
            myTabs.open(Tab.SKILLS);
            log("tab opened");
        }
        sleep(rand.pauseAfterTabs);

    }

    /*prevents bans. Will randomly but rarely move the mouse to random locations.*/
    public void antiBanMouse() throws InterruptedException {
        log("antiBanMouse()");

        sleep((long)WOODCUTTING_SPEED * rand.mouseMovementTimerBefore);
        getMouse().move(rand.randomMouseX,rand.randomMouseY);
        sleep(rand.mouseMovementTimerAfter);

    }

    /*prevents bans. Will randomly but extremely rarely put character in idle state in order to appear
     * AFK. Will break idle if character has low health. Adds 30% to idleCounter */
    public void antiBanLongAFK() throws InterruptedException {
        log("antiBanLongAFK()");

        sleep((long) (rand.longAFKTimer * WOODCUTTING_SPEED / 2));
        idleCounter += MAX_IDLE_TIMER * .30;
    }
}

