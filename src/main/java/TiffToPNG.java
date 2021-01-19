import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class TiffToPNG {


    public static void main(String[] args) {

        convertTiffToPNG();

    }

    public static void convertTiffToPNG() {
        File tiffFile = new File("C:\\Tiffs\\exemplo.tiff");

        try {

            ImageInputStream is = ImageIO.createImageInputStream(tiffFile);
            if (is == null || is.length() == 0){
                throw new IOException("Erro ao abrir o arquivo.");
            }

            Iterator<ImageReader> iterator = ImageIO.getImageReaders(is);
            if (iterator == null || !iterator.hasNext()) {
                throw new IOException("Formato de imagem não suportado.");
            }

            ImageReader reader = iterator.next();
            reader.setInput(is);

            int numeroDePaginas = reader.getNumImages(true);

            String pathName = "C:\\Tiffs\\";
            for(int i = 0; i < numeroDePaginas; i++){
                BufferedImage image = reader.read(i);
                String fileName = tiffFile.getName().replace(".tiff", "-"+(i+1)+".png");
                ImageIO.write(image, "png", new File(pathName + fileName));
            }


        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

