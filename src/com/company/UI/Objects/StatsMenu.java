package com.company.UI.Objects;

import com.company.Domain.Models.GameFactory;
import com.company.Domain.Utility.Coordinate;
import com.company.Domain.Models.Inventory;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import com.company.Enums.AtomType;
import com.company.Enums.PowerUpType;
import javax.imageio.ImageIO;
import com.company.UI.Objects.*;

public class StatsMenu {
    private GameFactory gameFactory;
    private Inventory inventory;
    public StatsMenu() {
//s
    }

    {
        JFrame frame = new JFrame("SideBar");
        JPanel panel = new JPanel();
        panel.setBounds(40,80,248,480);
        panel.setBackground(Color.BLACK);
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        JLabel score = new JLabel("Score :");//getScore//

        score.setForeground(Color.black);

        JButton eta=new JButton("Eta Shield");
        eta.setForeground(Color.BLUE);
        JButton lota=new JButton("Lota Shield");
        lota.setForeground(Color.BLUE);

        JButton theta = new JButton("Theta Shield");
        theta.setForeground(Color.BLUE);
        JButton zeta = new JButton("Zeta Shield");
        zeta.setForeground(Color.BLUE);

//panel bounds (unused)
        //panel.setBounds(windowWidth,-10, windowWidthExtended-windowWidth, windowHeight);


// score
        //JLabel score = new JLabel("Score: " + String.valueOf(gameFactory.getScore()) );
        //score.setLocation(0,0);
        //panel.add(score);

// clock
        // ImageIcon clock = new ImageIcon(getClass().getResource(""));
        // Image clockscaled = clock.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        // JLabel time = new JLabel(new ImageIcon(clockscaled));
        // Score.add(time);
        //JLabel timeLabel = new JLabel(String.valueOf(gameFactory.getTime()));
        //timeLabel.setLocation(2,2);
        ////panel.add(timeLabel);

//health
        // ImageIcon healthIcon = new ImageIcon(getClass().getResource(""));
        // Image healthscaled = clock.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        //  JLabel health = new JLabel(new ImageIcon(healthscaled));
        //  Score.add(health);
        //  JLabel healthLabel = new JLabel(String.valueOf());
        //  Score.add(healthLabel);
        //
//powerups
        ImageIcon alphapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+alpha-b.png"));
        Image alphapow = alphapowerup.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
        JLabel powalpha = new JLabel(new ImageIcon(alphapow));
        //powalpha.setLocation(6,0);

        //JLabel powAlphaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.ALPHA)));
        //powAlphaAmountLabel.setLocation(6,2);
        //panel.add(powAlphaAmountLabel);

         ImageIcon betapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+beta-b.png"));
         Image betapow = betapowerup.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         JLabel powbeta = new JLabel(new ImageIcon(betapow));
        //powbeta.setLocation(8,0);
        // panel.add(powbeta);
        // JLabel powBetaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.BETA)));
        //powBetaAmountLabel.setLocation(8,2);
        // panel.add(powAlphaAmountLabel);

         ImageIcon gammapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+gamma-b.png"));
         Image gammapow = gammapowerup.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         JLabel powgamma = new JLabel(new ImageIcon(gammapow));
        //powgamma.setLocation(10,0);
        //panel.add(powalpha);
        // JLabel powGammaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.GAMMA)));
        //powGammaAmountLabel.setLocation(10,2);
        // panel.add(powGammaAmountLabel );

        ImageIcon Sigmapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+sigma-b.png"));
         Image sigmapow = Sigmapowerup.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
         JLabel powsigma = new JLabel(new ImageIcon(sigmapow));
        //powsigma.setLocation(10,0);
        ////panel.add(powsigma);
        // JLabel powSigmaAmountLabel = new JLabel(String.valueOf(inventory.getPowerUpAmount(PowerUpType.SIGMA)));
        //powSigmaAmountLabel.setLocation(10,2);
        // panel.add(powSigmaAmountLabel);
//inventory atom amounts
        ImageIcon mixer = new ImageIcon(getClass().getResource("Assets/mixer.png"));
        Image mixerImage = mixer.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        JLabel mixerLabel = new JLabel( new ImageIcon(mixerImage));
        mixerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        //mixerLabel.setLocation(12,50);
        //panel.add(mixerLabel);


        ImageIcon alpha = new ImageIcon(getClass().getResource("Assets/atoms/alpha.png"));
        Image scaledAlpha = alpha.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel alphaLabel = new JLabel(new ImageIcon(scaledAlpha));
        //alphaLabel.setLocation(14,0);
        ImageIcon beta = new ImageIcon(getClass().getResource("Assets/atoms/beta.png"));
         Image scaledBeta = beta.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel betaLabel = new JLabel(new ImageIcon(scaledBeta));
       // betaLabel.setLocation(16,0);

         ImageIcon gamma = new ImageIcon(getClass().getResource("Assets/atoms/gamma.png"));
        Image scaledGamma = gamma.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
        JLabel gammaLabel = new JLabel(new ImageIcon(scaledGamma));
        //gammaLabel.setLocation(18,0);

        ImageIcon sigma = new ImageIcon(getClass().getResource("Assets/atoms/sigma.png"));
        Image scaledSigma = sigma.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH); // scales the image
         JLabel sigmaLabel = new JLabel(new ImageIcon(scaledSigma));
        //sigmaLabel.setLocation(20,0);

        //panel.add(alphaLabel);
        // JLabel alphaAmountLable = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.ALPHA)));
        //alphaAmountLable.setLocation(14,2);
        //panel.add(alphaAmountLable);

        //panel.add(betaLabel);
        // JLabel betaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.BETA)));
       // betaAmountLabel.setLocation(16,2);
        // panel.add(betaAmountLabel);

        // panel.add(gammaLabel);
       // JLabel gammaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.GAMMA)));
       // gammaAmountLabel.setLocation(18,2);
       // panel.add(gammaAmountLabel);

        // panel.add(sigmaLabel);
       // JLabel sigmaAmountLabel = new JLabel(String.valueOf(inventory.getAtomAmount(AtomType.SIGMA)));
       // sigmaAmountLabel.setLocation(20,2);
       // panel.add(sigmaAmountLabel);
//panel design
         panel.add(score);
        // panel.add(health);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(powalpha);
        panel.add(powbeta);
        panel.add(powgamma);
        panel.add(powsigma);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(mixerLabel);
        panel.add(alphaLabel);
        panel.add(betaLabel);
        panel.add(gammaLabel);
        panel.add(sigmaLabel);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(eta);
        panel.add(lota);
        panel.add(theta);
        panel.add(zeta);
        frame.add(panel);
        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    public static void main(String args[])
    {
        new StatsMenu();
    }
}



