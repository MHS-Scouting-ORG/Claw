package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase{
    
    private final DoubleSolenoid solenoid;
    private final CANSparkMax wristMotor;
    private RelativeEncoder wristEnc;

    public ClawSubsystem(){
        solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 1);
        wristMotor = new CANSparkMax(1, MotorType.kBrushless);
        wristEnc = wristMotor.getEncoder();
    }

    // Encoder methods
    public void resetWristEnc(){
        wristEnc.setPosition(0);
    }
    public double getWristEnc(){
        return wristEnc.getPosition();
    }

    //Turning Methods
    public void stopWrist(){
        wristMotor.set(0);
    }
    public void turnCW(){
        wristMotor.set(.3);
    }
    public void turnCCW(){
        wristMotor.set(-.3);
    }

    //Clamping Methods
    public void release(){
        solenoid.set(Value.kReverse);
    }
    public void clamp(){
        solenoid.set(Value.kForward);
    }

    // Angle Limiter Methods
    public void rotCWLimit(){
        if(wristEnc.getPosition() > 10){
            stopWrist();
        }
        turnCW();
    } 
    public void rotCCWLimit(){
        if(wristEnc.getPosition() < -10){
            stopWrist();
        }
        turnCCW();
    }

    @Override
    public void periodic(){}
}
