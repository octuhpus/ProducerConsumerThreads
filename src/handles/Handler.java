package handles;

import java.util.Arrays;
import java.util.Collections;

public class Handler {
	
	public static final int MAX_BUFFER_SIZE = 10;
	public static Integer[] buffer = new Integer[MAX_BUFFER_SIZE];
	public int lastZero = 0;

	public static void main(String[] args) {
	
		Handler h = new Handler();
		
		Producer p = new Producer(h);
		Consumer c = new Consumer(h);
		
		p.setConsume(c);
		c.setProduce(p);
		
		p.startProduce();
		c.startConsuming();
		
		try {
			p.produce.join();
			c.consume.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public Handler() {
		for (int i = 0; i < MAX_BUFFER_SIZE; i++) {
			buffer[i] = 0;
		}
		
	}
	
	
	public synchronized void setBuff(int num) {
		buffer[lastZero] = num;
		lastZero++;
		updateBuff();
		
	}
	
	
	public int getBuffItem(int i) {
		return buffer[i];
	}
	
	
	public synchronized void consumeBuffAtBottom() {
		
		
		buffer[0] = 0;
		updateBuff();
		lastZero--;
	}
	
	
	public synchronized void updateBuff() {
		
		
		if (buffer[0] == 0 && buffer[1] == 1) {
			Arrays.sort(buffer, Collections.reverseOrder());
		}
		
		for (int i = 0; i < MAX_BUFFER_SIZE - 1; i++) {
			System.out.printf ("%d ", buffer[i]);
		}
		
		System.out.println(buffer[MAX_BUFFER_SIZE - 1]);
		System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	public void hNot(Handler h) {
		h.notifyAll();
	}

}