package headfirstJava;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMiniMusicApp {
	
	public static void main(String[] args) {
		MiniMiniMusicApp mini = new MiniMiniMusicApp();
		mini.play();
	}
	
	public void play() {
		
		try {
			
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			
			Sequence seq = new Sequence(Sequence.PPQ, 4);
			
			Track track = seq.createTrack();
			
			ShortMessage aInst = new ShortMessage();
			aInst.setMessage(192, 1, 127, 0);
			MidiEvent instr = new MidiEvent(aInst, 0);
			track.add(instr);
			
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, 50, 100);
			MidiEvent noteOn = new MidiEvent(a, 1);
			track.add(noteOn);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 50, 100);
			MidiEvent noteOff = new MidiEvent(b, 16);
			track.add(noteOff);
			
			player.setSequence(seq);
			
			player.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
