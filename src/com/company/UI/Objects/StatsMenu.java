package com.company.UI.Objects;

import com.company.Domain.Controller.ShieldHandler;
import com.company.Domain.Models.GameFactory;
import com.company.Domain.Models.Inventory;
import com.company.Enums.AtomType;
import com.company.Enums.PowerUpType;
import com.company.Enums.ShieldType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class StatsMenu extends JPanel {

    public void drawScore(){
        GameWindowFactory gameWindow = GameWindowFactory.getInstance();
        this.setLayout(new GridLayout(13,1));

        JLabel score = new JLabel("Score: " +  String.valueOf(GameFactory.getInstance().getScore()));
        JLabel time = new JLabel("Time: " + String.valueOf(GameFactory.getInstance().getTime()));
        JLabel health = new JLabel("Health: " + "health.toString");


        ShieldHandler   shieldHandler = new ShieldHandler();
        score.setForeground(Color.black);

        JButton eta=new JButton("Eta Shield");
        eta.setForeground(Color.BLUE);
        JLabel etaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getShieldAmount(ShieldType.ETA)));

        eta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){shieldHandler.addShield(ShieldType.ETA);}
        });
        JButton lota=new JButton("Lota Shield");
        lota.setForeground(Color.BLUE);
        JLabel lotaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getShieldAmount(ShieldType.LOTA)));
        lota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                    shieldHandler.addShield(ShieldType.LOTA);
            }
        });
        JButton theta = new JButton("Theta Shield");
        theta.setForeground(Color.BLUE);
        JLabel thetaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getShieldAmount(ShieldType.THETA)));
        theta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shieldHandler.addShield(ShieldType.THETA);
            }
        });
        JButton zeta = new JButton("Zeta Shield");
        zeta.setForeground(Color.BLUE);
        JLabel zetaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getShieldAmount(ShieldType.ZETA)));
        zeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shieldHandler.addShield(ShieldType.ZETA);
            }
        });


// health
        //JLabel health = new JLabel("Health: " + String.valueOf(GameFactory.getInstance().getHealth()));

//powerups
        ImageIcon alphapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+alpha-b.png"));
        Image alphapow = alphapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton powalpha = new JButton();;
        powalpha.setIcon(new ImageIcon(alphapow));
        powalpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powalpha.setIcon(getGray(new ImageIcon(alphapow)));

            }
        });

        JLabel powAlphaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getPowerUpAmount(PowerUpType.ALPHA)));


        ImageIcon betapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+beta-b.png"));
        Image betapow = betapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton powbeta = new JButton(new ImageIcon(betapow));
        powalpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powalpha.setIcon(getGray(new ImageIcon(betapow)));
            }
        });

        JLabel powBetaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getPowerUpAmount(PowerUpType.BETA)));


        ImageIcon gammapowerup = new ImageIcon(getClass().getResource("Assets/powerups/+gamma-b.png"));
        Image gammapow = gammapowerup.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton powgamma = new JButton(new ImageIcon(gammapow));
        powalpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powalpha.setIcon(getGray(new ImageIcon(gammapow)));
            }
        });

        JLabel powGammaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getPowerUpAmount(PowerUpType.GAMMA)));

        ImageIcon sigmaPowerUp = new ImageIcon(getClass().getResource("Assets/powerups/+sigma-b.png"));
        Image sigmapow = sigmaPowerUp.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton powsigma = new JButton(new ImageIcon(sigmapow));
        powalpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                powalpha.setIcon(getGray(new ImageIcon(sigmapow)));
            }
        });


        JLabel powSigmaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getPowerUpAmount(PowerUpType.SIGMA)));

//inventory atom amounts
        ImageIcon mixer = new ImageIcon(getClass().getResource("Assets/mixer.png"));
        Image mixerImage = mixer.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel mixerLabel = new JLabel( new ImageIcon(mixerImage));
        mixerLabel.setHorizontalAlignment(SwingConstants.RIGHT);




        ImageIcon alpha = new ImageIcon(getClass().getResource("Assets/atoms/alpha.png"));
        Image scaledAlpha = alpha.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel alphaLabel = new JLabel(new ImageIcon(scaledAlpha));


        ImageIcon beta = new ImageIcon(getClass().getResource("Assets/atoms/beta.png"));
        Image scaledBeta = beta.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel betaLabel = new JLabel(new ImageIcon(scaledBeta));


        ImageIcon gamma = new ImageIcon(getClass().getResource("Assets/atoms/gamma.png"));
        Image scaledGamma = gamma.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel gammaLabel = new JLabel(new ImageIcon(scaledGamma));


        ImageIcon sigma = new ImageIcon(getClass().getResource("Assets/atoms/sigma.png"));
        Image scaledSigma = sigma.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scales the image
        JLabel sigmaLabel = new JLabel(new ImageIcon(scaledSigma));


         JLabel alphaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getAtomAmount(AtomType.ALPHA)));

         JLabel betaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getAtomAmount(AtomType.BETA)));

         JLabel gammaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getAtomAmount(AtomType.GAMMA)));

        //JLabel sigmaAmountLabel = new JLabel("Amount");
        JLabel sigmaAmountLabel = new JLabel(String.valueOf(Inventory.getInstance().getAtomAmount(AtomType.SIGMA)));
        JLabel emp = new JLabel();

//panel design
        this.add(score);
        this.add(emp);
        this.add(time);
        this.add(emp);
        this.add(health);
        this.add(emp);
      //  this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(powalpha);
        this.add(powAlphaAmountLabel);
        this.add(powbeta);
        this.add(powBetaAmountLabel);
        this.add(powgamma);
        this.add(powGammaAmountLabel);
        this.add(powsigma);
        this.add(powSigmaAmountLabel);
      //  this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(mixerLabel);
        this.add(emp);
        this.add(alphaLabel);
        this.add(alphaAmountLabel);
        this.add(betaLabel);
        this.add(betaAmountLabel);
        this.add(gammaLabel);
        this.add(gammaAmountLabel);
        this.add(sigmaLabel);
        this.add(sigmaAmountLabel);
       // this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(eta);
        this.add(etaAmountLabel);
        this.add(lota);
        this.add(lotaAmountLabel);
        this.add(theta);
        this.add(thetaAmountLabel);
        this.add(zeta);
        this.add(zetaAmountLabel);

    }

    private Icon getGray(Icon icon) {
        final int w = icon.getIconWidth();
        final int h = icon.getIconHeight();
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        Image gray = GrayFilter.createDisabledImage(image);
        return new ImageIcon(gray);
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
