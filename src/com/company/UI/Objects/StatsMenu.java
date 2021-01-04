package com.company.UI.Objects;

import com.company.Domain.Utility.Coordinate;

import javax.swing.*;
import java.awt.*;

public class StatsMenu extends JPanel {

    public void drawScore(){
        /*this.setBounds(0,0,248,480);*/
        /*this.setOpaque(false);*/
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JLabel score = new JLabel("Score: " + "Score.toString");
        JLabel time = new JLabel("Time: " + "time.toString");
        JLabel health = new JLabel("Health: " + "health.toString");


        score.setForeground(Color.black);

        JButton eta=new JButton("Eta Shield");
        eta.setForeground(Color.BLUE);
        JButton lota=new JButton("Lota Shield");
        lota.setForeground(Color.BLUE);

        JButton theta = new JButton("Theta Shield");
        theta.setForeground(Color.BLUE);
        JButton zeta = new JButton("Zeta Shield");
        zeta.setForeground(Color.BLUE);


// score
        //JLabel score = new JLabel("Score: " + String.valueOf(gameFactory.getScore()) );

// clock
        //JLabel timeLabel = new JLabel("Time: " + String.valueOf(gameFactory.getTime()));

// health
        //JLabel health = new JLabel("Health: " + String.valueOf(gameFactory.getHealth()));

//powerups
        ImageIcon alphapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+alpha-b.png"));
        Image alphapow = alphapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        JLabel powalpha = new JLabel(new ImageIcon(alphapow));
        JLabel powAlphaAmountLabel = new JLabel("Amount");


        //JLabel powAlphaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.ALPHA)));
        //powAlphaAmountLabel.setLocation(6,2);
        //panel.add(powAlphaAmountLabel);

        ImageIcon betapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+beta-b.png"));
        Image betapow = betapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel powbeta = new JLabel(new ImageIcon(betapow));
        JLabel powBetaAmountLabel = new JLabel("Amount");


        // JLabel powBetaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.BETA)));
        //powBetaAmountLabel.setLocation(8,2);
        // panel.add(powAlphaAmountLabel);

        ImageIcon gammapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+gamma-b.png"));
        Image gammapow = gammapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel powgamma = new JLabel(new ImageIcon(gammapow));
        JLabel powGammaAmountLabel = new JLabel("Amount");

        // JLabel powGammaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.GAMMA)));
        //powGammaAmountLabel.setLocation(10,2);
        // panel.add(powGammaAmountLabel );

        ImageIcon Sigmapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+sigma-b.png"));
        Image sigmapow = Sigmapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel powsigma = new JLabel(new ImageIcon(sigmapow));
        JLabel powSigmaAmountLabel = new JLabel("Amount");

        // JLabel powSigmaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.SIGMA)));
        //powSigmaAmountLabel.setLocation(10,2);
        // panel.add(powSigmaAmountLabel);
//inventory atom amounts
        ImageIcon mixer = new ImageIcon(getClass().getResource("Assets/mixer.png"));
        Image mixerImage = mixer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel mixerLabel = new JLabel( new ImageIcon(mixerImage));
        mixerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //mixerLabel.setLocation(12,50);



        ImageIcon alpha = new ImageIcon(getClass().getResource("Assets/atoms/alpha.png"));
        Image scaledAlpha = alpha.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel alphaLabel = new JLabel(new ImageIcon(scaledAlpha));

        //alphaLabel.setLocation(14,0);
        ImageIcon beta = new ImageIcon(getClass().getResource("Assets/atoms/beta.png"));
        Image scaledBeta = beta.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel betaLabel = new JLabel(new ImageIcon(scaledBeta));
        // betaLabel.setLocation(16,0);

        ImageIcon gamma = new ImageIcon(getClass().getResource("Assets/atoms/gamma.png"));
        Image scaledGamma = gamma.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel gammaLabel = new JLabel(new ImageIcon(scaledGamma));
        //gammaLabel.setLocation(18,0);

        ImageIcon sigma = new ImageIcon(getClass().getResource("Assets/atoms/sigma.png"));
        Image scaledSigma = sigma.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel sigmaLabel = new JLabel(new ImageIcon(scaledSigma));
        //sigmaLabel.setLocation(20,0);

        JLabel alphaAmountLabel = new JLabel("Amount");
        // JLabel alphaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.ALPHA)));
        //alphaAmountLable.setLocation(14,2);
        //panel.add(alphaAmountLable);

        JLabel betaAmountLabel = new JLabel("Amount");
        // JLabel betaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.BETA)));
        // betaAmountLabel.setLocation(16,2);
        // panel.add(betaAmountLabel);

        JLabel gammaAmountLabel = new JLabel("Amount");
        // JLabel gammaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.GAMMA)));
        // gammaAmountLabel.setLocation(18,2);
        // panel.add(gammaAmountLabel);

        JLabel sigmaAmountLabel = new JLabel("Amount");
        // JLabel sigmaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.SIGMA)));
        // sigmaAmountLabel.setLocation(20,2);
        // panel.add(sigmaAmountLabel);
//panel design
        this.add(score);
        this.add(time);
        this.add(health);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(powalpha);
        this.add(powAlphaAmountLabel);
        this.add(powbeta);
        this.add(powBetaAmountLabel);
        this.add(powgamma);
        this.add(powGammaAmountLabel);
        this.add(powsigma);
        this.add(powSigmaAmountLabel);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(mixerLabel);
        this.add(alphaLabel);
        this.add(alphaAmountLabel);
        this.add(betaLabel);
        this.add(betaAmountLabel);
        this.add(gammaLabel);
        this.add(gammaAmountLabel);
        this.add(sigmaLabel);
        this.add(sigmaAmountLabel);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(eta);
        this.add(lota);
        this.add(theta);
        this.add(zeta);
        /*frame.add(this);
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);*/
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }

    public void draw() {
        drawScore();
        this.setBounds(GameWindowFactory.getGameWindowWidth(),0,400,GameWindowFactory.getWindowHeight() - 35);
        this.setOpaque(true);
        this.setBackground(new Color(102, 102, 102, 123));
        this.setFocusable(false);
    }
}
