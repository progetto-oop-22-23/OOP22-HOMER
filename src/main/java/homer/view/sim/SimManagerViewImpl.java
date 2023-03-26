package homer.view.sim;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;

import homer.core.SimManagerViewObserver;

/**
 * Implementation of {@link SimManagerView}
 */
public class SimManagerViewImpl implements SimManagerView {

    private static final String FRAME_TITLE = "Simulation Manager";
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final Dimension WINDOW_DIMENSION = new Dimension(500, 500);
    private SimManagerViewObserver simManager;
    private final JFrame frame = new JFrame(FRAME_TITLE);

    /**
     * Creates and display a new {@link SimManagerView}.
     */
    public SimManagerViewImpl() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_DIMENSION);

        final JPanel panel = new JPanel();
        final JButton resume = new JButton(RESUME);
        final JButton pause = new JButton(PAUSE);
        panel.add(resume);
        panel.add(pause);
        frame.getContentPane().add(panel);

        resume.addActionListener(e -> simManager.resume());
        pause.addActionListener(e -> simManager.pause());

        frame.pack();
        frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

}
