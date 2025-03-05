import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(info = "Wood and Drop", logo = "", version = 1.0, author = "p0ntus", name = "Woodcutter")

public class Woodcutter extends Script{

    private int idleCounter = 0;
    RandomizerHelper rand;

    static String WOODCUTTING_TYPE;
    static int[] INVENTORY_TO_KEEP = new int[8];
    static int[] WOOD_CUT_IDS = new int[8];
    static int WOODCUTTING_SPEED = 1;
    static int[] TREE_IDS = new int[8];

    @Override
    public void onStart() throws InterruptedException {
        GUI gui = new GUI();
        getBot().getScriptExecutor().pause();

        while (gui.isActive() || gui.isVisible) {
            sleep(1427);
            log("SLEEPING @onStart()");
        }

        if (!gui.isVisible() || !gui.isActive)
            getBot().getScriptExecutor().resume();

        if (INVENTORY_TO_KEEP == null || WOOD_CUT_IDS == null ||
                WOODCUTTING_TYPE == null)
            stop();
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

    public void cutWood() throws InterruptedException {
        log("cutWood()");
        boolean triedToCut = false;

        NPC tree = getNpcs().closest(TREE_IDS);
        if (isReadyToCut() && tree != null && !getInventory().isFull()) {
            sleep(rand.hoverTime);
            triedToCut = tree.interact(WOODCUTTING_TYPE);
        }
        sleep(rand.waitAfterFinishClick);

        if(triedToCut)
            idleCounter = 0;

        log("Cut successful = " + triedToCut);
    }

    private void closeDialog() throws InterruptedException {
        log("Closing Dialog");

        if (getWidgets().getWidgetContainingText("Click here to continue") != null) {
            sleep((long)(rand.closeDialogueTimer * WOODCUTTING_SPEED));
            getKeyboard().typeKey(' ');
        }
        idleCounter++;
    }
}
