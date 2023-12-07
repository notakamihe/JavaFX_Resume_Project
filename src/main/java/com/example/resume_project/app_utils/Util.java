package com.example.resume_project.app_utils;

import com.example.resume_project.Main;
import com.example.resume_project.models.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static String colorToHexString(Color color) {
        return String.format(
                "#%02X%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255),
                (int) (color.getOpacity() * 255)
        );
    }

    public static ImageView createAvatar(Image image, int size) {
        int imageWidth = (int) image.getWidth();
        int imageHeight = (int) image.getHeight();
        boolean tall = ((float) imageWidth / (float) imageHeight) < 1;
        int cropSize = tall ? imageWidth : imageHeight;

        WritableImage writableImage = new WritableImage(cropSize, cropSize);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = writableImage.getPixelWriter();

        int offset = ((tall ? imageHeight : imageWidth) - cropSize) / 2;

        for (int x = 0; x < cropSize; x++) {
            for (int y = 0; y < cropSize; y++) {
                if (tall)
                    writer.setColor(x, y, reader.getColor(x, y + offset));
                else
                    writer.setColor(x, y, reader.getColor(x + offset, y));
            }
        }

        ImageView view = new ImageView(writableImage);

        view.setFitWidth(size);
        view.setPreserveRatio(true);
        view.setClip(new Circle(size / 2, size / 2, size / 2));

        return view;
    }

    public static Resume getExampleResume() {
        Education[] educationExp = {
                new Education("January 2014", "May 2018", "Bachelor of Science", "Computer Science",
                        "University of Central Missouri", "Warrensburg, MO, USA"),
                new Education("January 2019", "September 2021", "Master of Science", "Computer Science",
                        "University of Kansas", "Lawrence, KS, USA")
        };
        Work[] workExp = {
                new Work("October 2017", "June 2018", "Software Developer Intern", "XYZ Tech Solutions",
                        "Chicago, IL, USA",
                        new Objective[] {
                                new Objective("Collaborated on the development team to design and implement new features for web applications."),
                                new Objective("Assisted in debugging and troubleshooting software issues."),
                                new Objective("Participated in code reviews and provided constructive feedback.")
                        }),
                new Work("June 2018", "Present", "Software Developer", "ABC Tech Incorporated",
                        "Springfield, IL, USA",
                        new Objective[] {
                                new Objective("Led the development of a new feature for a customer-facing application, resulting in a 20% increase in user engagement."),
                                new Objective("Collaborated with the QA team to conduct thorough testing, ensuring the delivery of high-quality software.")
                        })
        };

        Resume resume = new Resume("John Doe", "Software Developer", "1234 Address Street, Kansas City, MO 64000",
                "(555) 123-4567", "john.doe@email.com", "Results-driven and detail-oriented software engineer with a Bachelor's degree in Computer Science. Proven ability to design and implement innovative solutions to complex problems. Adept at collaborating with cross-functional teams to deliver high-quality software applications. Eager to contribute technical skills and passion for coding to a dynamic development team.",
                workExp, educationExp,
                new String[] {"Team Collaboration", "Communication", "Problem Solving", "Time Management"},
                new String[]{
                        "Programming Languages: Java, Python, C++",
                        "Web Development: HTML, CSS, JavaScript",
                        "Database Management: SQL",
                        "Software Development Tools: Git, Visual Studio Code",
                        "Problem-solving and Algorithm Design", "Defending"
                }, new Image(Main.class.getResourceAsStream("/images/avatar.png")));
        resume.setTitle("MyResume");

        return resume;
    }

    public static void goTo(String fxmlFile, Scene scene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
            scene.setRoot(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage imageToBufferedImage(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        PixelReader pixelReader = image.getPixelReader();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = pixelReader.getArgb(x, y);
                bufferedImage.setRGB(x, y, argb);
            }
        }

        return bufferedImage;
    }

    public static InputStream imageToStream(Image image) {
        ByteArrayOutputStream binaryStream = new ByteArrayOutputStream();

        if (image == null)
            return null;

        try {
            ImageIO.write(imageToBufferedImage(image), "PNG", binaryStream);
            return new ByteArrayInputStream(binaryStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    public static Image streamToImage(InputStream binaryStream) {
        try {
            return new Image(new ByteArrayInputStream(binaryStream.readAllBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
