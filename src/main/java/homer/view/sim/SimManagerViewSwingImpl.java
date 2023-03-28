package homer.view.sim;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.time.Duration;

import homer.core.SimManagerViewObserver;

/**
 * Implementation of {@link SimManagerView} in Swing.
 */
public final class SimManagerViewSwingImpl implements SimManagerView {

    private static final String FRAME_TITLE = "Simulation Manager";
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final String ONE_SEC = "1s";
    private static final String ONE_MIN = "1m";
    private static final String ONE_HOUR = "1h";
    private static final double HEIGHT_SCALE = 0.2;
    private static final double WIDTH_SCALE = 0.2;
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private SimManagerViewObserver simManager;

    /**
     * Creates and display a new {@link SimManagerViewSwingImpl}.
     */
    public SimManagerViewSwingImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final var screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.getWidth() * WIDTH_SCALE), (int) (screenSize.getHeight() * HEIGHT_SCALE));

        final JPanel panel = new JPanel();
        final JButton resume = new JButton(RESUME);
        final JButton pause = new JButton(PAUSE);
        final JButton oneSec = new JButton(ONE_SEC);
        final JButton oneMinute = new JButton(ONE_MIN);
        final JButton oneHour = new JButton(ONE_HOUR);
        panel.add(resume);
        panel.add(pause);
        panel.add(oneSec);
        panel.add(oneMinute);
        panel.add(oneHour);
        frame.getContentPane().add(panel);

        resume.addActionListener(e -> simManager.resume());
        pause.addActionListener(e -> simManager.pause());
        oneSec.addActionListener(e -> simManager.setSimStepPeriod(Duration.ofSeconds(1)));
        oneMinute.addActionListener(e -> simManager.setSimStepPeriod(Duration.ofMinutes(1)));
        oneHour.addActionListener(e -> simManager.setSimStepPeriod(Duration.ofHours(1)));

        frame.pack();
        frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

}
