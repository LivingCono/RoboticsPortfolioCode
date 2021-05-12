/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  //private Joystick m_leftStick;
  private XboxController xboxController;
  private CANSparkMax leftFront;
  private CANSparkMax leftBack;
  private CANSparkMax rightFront;
  private CANSparkMax rightBack; 
  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;
  private SpeedControllerGroup leftSide;
  private SpeedControllerGroup rightSide;
   
  @Override
  public void robotInit() {

    leftFront = new CANSparkMax(11, MotorType.kBrushless);
    leftFront.setInverted(false);
    leftBack = new CANSparkMax(10,MotorType.kBrushless);
    leftBack.setInverted(false);
    rightFront = new CANSparkMax(1, MotorType.kBrushless);
    rightFront.setInverted(false);
    rightBack = new CANSparkMax(3,MotorType.kBrushless);
    rightBack.setInverted(false);

    //leftBack.follow(leftFront);
    //rightBack.follow(rightFront);

    leftSide = new SpeedControllerGroup(leftFront, leftBack);
    rightSide = new SpeedControllerGroup(rightFront, rightBack);

    m_myRobot = new DifferentialDrive(leftSide , rightSide);


    xboxController = new XboxController(0);

    //leftEncoder = leftFront.getEncoder();
    //rightEncoder = rightFront.getEncoder(); 

  }

  @Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(xboxController.getY(Hand.kLeft) * -.5 , xboxController.getY(Hand.kRight) * -.5);

    //System.out.println(leftEncoder.getPosition());

  }


  public void autonomousPeriodic() {
      m_myRobot.tankDrive(1 *.5, 1 * .5);
  }

}
