package handles;

import static handles.Handler.MAX_BUFFER_SIZE;

public class Producer implements Runnable{
	
	public Thread produce;
	Handler h;
	Consumer c;
	
	private int input = 1;
	
	public Producer (Handler h) {
		this.h = h;
		
		produce = new Thread(this);
	}
	
	public void startProduce() {
		this.produce.start();
	}

	@Override
	public void run() {
		
		synchronized(h) {
			while (true) {
				
				if (h.getBuffItem(MAX_BUFFER_SIZE-1) == 1) {
					
					System.out.printf("\nProducer Waiting\n");
					h.hNot(h);
					try {
						h.wait();
						//Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				
					continue;
				} 
			
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				h.setBuff(input);
				//h.updateBuff();
			}
		}
	}
	
	public void setConsume(Consumer c) {
		this.c = c;
	}
	
}
