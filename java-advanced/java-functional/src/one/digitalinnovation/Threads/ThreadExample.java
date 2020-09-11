package one.digitalinnovation.Threads;

public class ThreadExample {

    public static void main(String[] args) {

        // Asynchronous Threads Example

        pdfGenerator startPdfGenerator = new pdfGenerator();
        loadingBar startLoadingBar = new loadingBar(startPdfGenerator);

        startPdfGenerator.start();
        startLoadingBar.start();

    }
}

class pdfGenerator extends Thread {

    @Override
    public void run() {

        try {
            System.out.println("Starting PDF generation");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("PDF Generated");
    }
}

class loadingBar extends Thread {

    private Thread startPdfGenerator;

    public loadingBar(Thread startPdfGenerator) {
        this.startPdfGenerator = startPdfGenerator;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(500);

                if (!startPdfGenerator.isAlive()) {
                    break;
                }
                System.out.println("Loading...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}