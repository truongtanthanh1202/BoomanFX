package booman_fx.game.GameResources;

import booman_fx.engine.ImageSheet;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class Images {
    private ImageSheet sheet;
    private final Image image;

    // constructor
    public Images(int x, int y, ImageSheet sheet) {
        this.sheet = sheet;
        this.image = load(x, y);
    }

    public Images(Image image) {
        this.image = image;
    }

    // load pixels and return an exact character/item image
    private Image load(int x, int y) {
        WritableImage wr = new WritableImage(sheet.getWidthImage(), sheet.getHeightImage());
        PixelWriter pw = wr.getPixelWriter();

        for (int h = 0; h < sheet.getHeightImage(); h++) {
            for (int w = 0; w < sheet.getWidthImage(); w++) {
                //tính tọa độ cụ thể để truy cập vào mảng
                int realCoordinateX = x * sheet.getWidthImage() + w;
                int realCoordinateY = y * sheet.getHeightImage() + h;
                //offset = 0 ; startX,Y = 0 (because of scan full picture); scansize = imgWidth
                //pixel   = rgbArray[offset + (y-startY)*scansize + (x-startX)];
                pw.setArgb(w, h, sheet.getPixels()[realCoordinateY * sheet.getWidthImageSheet() + realCoordinateX]);
            }
        }

        return wr;
    }

    // get image
    public Image getImage() {
        return image;
    }

    public static Images[] chooseMap = {
            new Images(0, 0, ImageSheet.imageMap),
            new Images(1, 0, ImageSheet.imageMap),
            new Images(2, 0, ImageSheet.imageMap),
            new Images(0, 1, ImageSheet.imageMap),
            new Images(1, 1, ImageSheet.imageMap),
            new Images(2, 1, ImageSheet.imageMap),
            new Images(0, 2, ImageSheet.imageMap)
    };

    public static Images[] buttonBack = {
            new Images(0, 0, ImageSheet.imageButton),
            new Images(1, 0, ImageSheet.imageButton)
    };

    public static Images[] buttonContinue = {
            new Images(0, 1, ImageSheet.imageButton),
            new Images(1, 1, ImageSheet.imageButton)
    };

    public static Images[] buttonBehind = {
            new Images(0, 2, ImageSheet.imageButton),
            new Images(1, 2, ImageSheet.imageButton)
    };

    public static Images[] buttonFront = {
            new Images(0, 3, ImageSheet.imageButton),
            new Images(1, 3, ImageSheet.imageButton)
    };

    public static Images[] buttonStart = {
            new Images(0, 4, ImageSheet.imageButton),
            new Images(1, 4, ImageSheet.imageButton)
    };

    public static Images[] buttonInstruct = {
            new Images(0, 5, ImageSheet.imageButton),
            new Images(1, 5, ImageSheet.imageButton)
    };

    public static Images[] buttonNewGame = {
            new Images(0, 8, ImageSheet.imageButton),
            new Images(1, 8, ImageSheet.imageButton)
    };

    public static Images[] iconHome = {
            new Images(0, 0, ImageSheet.imageIcon),
            new Images(1, 0, ImageSheet.imageIcon)
    };

    public static Images[] iconPause = {
            new Images(0, 1, ImageSheet.imageIcon),
            new Images(1, 1, ImageSheet.imageIcon)
    };

    public static Images[] iconContinue = {
            new Images(0, 2, ImageSheet.imageIcon),
            new Images(1, 2, ImageSheet.imageIcon)
    };

    public static Images[] iconOnSoundEffect = {
            new Images(0, 3, ImageSheet.imageIcon),
            new Images(1, 3, ImageSheet.imageIcon)
    };

    public static Images[] iconOffSoundEffect = {
            new Images(0, 4, ImageSheet.imageIcon),
            new Images(1, 4, ImageSheet.imageIcon)
    };

    public static Images[] iconOnMusic = {
            new Images(0, 5, ImageSheet.imageIcon),
            new Images(1, 5, ImageSheet.imageIcon)
    };

    public static Images[] iconOffMusic = {
            new Images(0, 6, ImageSheet.imageIcon),
            new Images(1, 6, ImageSheet.imageIcon)
    };

    public static Images[] imageStatus = {
            new Images(0, 0, ImageSheet.imageStatusGame),
            new Images(1, 0, ImageSheet.imageStatusGame),
            new Images(0, 1, ImageSheet.imageStatusGame)
    };

    public static Images[][][] boomer = {
            {
                    {
                            new Images(0, 0, ImageSheet.imageObjects),
                            new Images(1, 0, ImageSheet.imageObjects),
                            new Images(2, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 1, ImageSheet.imageObjects),
                            new Images(1, 1, ImageSheet.imageObjects),
                            new Images(2, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 2, ImageSheet.imageObjects),
                            new Images(1, 2, ImageSheet.imageObjects),
                            new Images(2, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 3, ImageSheet.imageObjects),
                            new Images(1, 3, ImageSheet.imageObjects),
                            new Images(2, 3, ImageSheet.imageObjects)
                    }
            },
            {

                    {
                            new Images(3, 0, ImageSheet.imageObjects),
                            new Images(4, 0, ImageSheet.imageObjects),
                            new Images(5, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 1, ImageSheet.imageObjects),
                            new Images(4, 1, ImageSheet.imageObjects),
                            new Images(5, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 2, ImageSheet.imageObjects),
                            new Images(4, 2, ImageSheet.imageObjects),
                            new Images(5, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 3, ImageSheet.imageObjects),
                            new Images(4, 3, ImageSheet.imageObjects),
                            new Images(5, 3, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(6, 0, ImageSheet.imageObjects),
                            new Images(7, 0, ImageSheet.imageObjects),
                            new Images(8, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 1, ImageSheet.imageObjects),
                            new Images(7, 1, ImageSheet.imageObjects),
                            new Images(8, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 2, ImageSheet.imageObjects),
                            new Images(7, 2, ImageSheet.imageObjects),
                            new Images(8, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 3, ImageSheet.imageObjects),
                            new Images(7, 3, ImageSheet.imageObjects),
                            new Images(8, 3, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(9, 0, ImageSheet.imageObjects),
                            new Images(10, 0, ImageSheet.imageObjects),
                            new Images(11, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 1, ImageSheet.imageObjects),
                            new Images(10, 1, ImageSheet.imageObjects),
                            new Images(11, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 2, ImageSheet.imageObjects),
                            new Images(10, 2, ImageSheet.imageObjects),
                            new Images(11, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 3, ImageSheet.imageObjects),
                            new Images(10, 3, ImageSheet.imageObjects),
                            new Images(11, 3, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(12, 0, ImageSheet.imageObjects),
                            new Images(13, 0, ImageSheet.imageObjects),
                            new Images(14, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 1, ImageSheet.imageObjects),
                            new Images(13, 1, ImageSheet.imageObjects),
                            new Images(14, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 2, ImageSheet.imageObjects),
                            new Images(13, 2, ImageSheet.imageObjects),
                            new Images(14, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 3, ImageSheet.imageObjects),
                            new Images(13, 3, ImageSheet.imageObjects),
                            new Images(14, 3, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(15, 0, ImageSheet.imageObjects),
                            new Images(16, 0, ImageSheet.imageObjects),
                            new Images(17, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(15, 1, ImageSheet.imageObjects),
                            new Images(16, 1, ImageSheet.imageObjects),
                            new Images(17, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(15, 2, ImageSheet.imageObjects),
                            new Images(16, 2, ImageSheet.imageObjects),
                            new Images(17, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(15, 3, ImageSheet.imageObjects),
                            new Images(16, 3, ImageSheet.imageObjects),
                            new Images(17, 3, ImageSheet.imageObjects)
                    }

            },
            {
                    {
                            new Images(18, 0, ImageSheet.imageObjects),
                            new Images(19, 0, ImageSheet.imageObjects),
                            new Images(20, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(18, 1, ImageSheet.imageObjects),
                            new Images(19, 1, ImageSheet.imageObjects),
                            new Images(20, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(18, 2, ImageSheet.imageObjects),
                            new Images(19, 2, ImageSheet.imageObjects),
                            new Images(20, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(18, 3, ImageSheet.imageObjects),
                            new Images(19, 3, ImageSheet.imageObjects),
                            new Images(20, 3, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(21, 0, ImageSheet.imageObjects),
                            new Images(22, 0, ImageSheet.imageObjects),
                            new Images(23, 0, ImageSheet.imageObjects)
                    },
                    {
                            new Images(21, 1, ImageSheet.imageObjects),
                            new Images(22, 1, ImageSheet.imageObjects),
                            new Images(23, 1, ImageSheet.imageObjects)
                    },
                    {
                            new Images(21, 2, ImageSheet.imageObjects),
                            new Images(22, 2, ImageSheet.imageObjects),
                            new Images(23, 2, ImageSheet.imageObjects)
                    },
                    {
                            new Images(21, 3, ImageSheet.imageObjects),
                            new Images(22, 3, ImageSheet.imageObjects),
                            new Images(23, 3, ImageSheet.imageObjects)
                    }
            }

    };

    public static Images[][][] enemy = {
            {
                    {
                            new Images(0, 5, ImageSheet.imageObjects),
                            new Images(1, 5, ImageSheet.imageObjects),
                            new Images(2, 5, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 6, ImageSheet.imageObjects),
                            new Images(1, 6, ImageSheet.imageObjects),
                            new Images(2, 6, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 7, ImageSheet.imageObjects),
                            new Images(1, 7, ImageSheet.imageObjects),
                            new Images(2, 7, ImageSheet.imageObjects)
                    },
                    {
                            new Images(0, 8, ImageSheet.imageObjects),
                            new Images(1, 8, ImageSheet.imageObjects),
                            new Images(2, 8, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(3, 5, ImageSheet.imageObjects),
                            new Images(4, 5, ImageSheet.imageObjects),
                            new Images(5, 5, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 6, ImageSheet.imageObjects),
                            new Images(4, 6, ImageSheet.imageObjects),
                            new Images(5, 6, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 7, ImageSheet.imageObjects),
                            new Images(4, 7, ImageSheet.imageObjects),
                            new Images(5, 7, ImageSheet.imageObjects)
                    },
                    {
                            new Images(3, 8, ImageSheet.imageObjects),
                            new Images(4, 8, ImageSheet.imageObjects),
                            new Images(5, 8, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(6, 5, ImageSheet.imageObjects),
                            new Images(7, 5, ImageSheet.imageObjects),
                            new Images(8, 5, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 6, ImageSheet.imageObjects),
                            new Images(7, 6, ImageSheet.imageObjects),
                            new Images(8, 6, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 7, ImageSheet.imageObjects),
                            new Images(7, 7, ImageSheet.imageObjects),
                            new Images(8, 7, ImageSheet.imageObjects)
                    },
                    {
                            new Images(6, 8, ImageSheet.imageObjects),
                            new Images(7, 8, ImageSheet.imageObjects),
                            new Images(8, 8, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(9, 5, ImageSheet.imageObjects),
                            new Images(10, 5, ImageSheet.imageObjects),
                            new Images(11, 5, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 6, ImageSheet.imageObjects),
                            new Images(10, 6, ImageSheet.imageObjects),
                            new Images(11, 6, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 7, ImageSheet.imageObjects),
                            new Images(10, 7, ImageSheet.imageObjects),
                            new Images(11, 7, ImageSheet.imageObjects)
                    },
                    {
                            new Images(9, 8, ImageSheet.imageObjects),
                            new Images(10, 8, ImageSheet.imageObjects),
                            new Images(11, 8, ImageSheet.imageObjects)
                    }
            },
            {
                    {
                            new Images(12, 5, ImageSheet.imageObjects),
                            new Images(13, 5, ImageSheet.imageObjects),
                            new Images(14, 5, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 6, ImageSheet.imageObjects),
                            new Images(13, 6, ImageSheet.imageObjects),
                            new Images(14, 6, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 7, ImageSheet.imageObjects),
                            new Images(13, 7, ImageSheet.imageObjects),
                            new Images(14, 7, ImageSheet.imageObjects)
                    },
                    {
                            new Images(12, 8, ImageSheet.imageObjects),
                            new Images(13, 8, ImageSheet.imageObjects),
                            new Images(14, 8, ImageSheet.imageObjects)
                    }
            }
    };

    public static Images[] bomb = {
            new Images(1, 10, ImageSheet.imageObjects),
            new Images(0, 10, ImageSheet.imageObjects)
    };

    public static Images[] explodes = {
            new Images(2, 10, ImageSheet.imageObjects),
            new Images(3, 10, ImageSheet.imageObjects),
            new Images(4, 10, ImageSheet.imageObjects),
            new Images(5, 10, ImageSheet.imageObjects),
            new Images(6, 10, ImageSheet.imageObjects),
            new Images(7, 10, ImageSheet.imageObjects),
            new Images(8, 10, ImageSheet.imageObjects)
    };

    public static Images[] portal = {
            new Images(9, 10, ImageSheet.imageObjects),
            new Images(10, 10, ImageSheet.imageObjects),
            new Images(11, 10, ImageSheet.imageObjects)
    };

    public static Images[][] items = {
            {
                    new Images(0, 11, ImageSheet.imageObjects),
                    new Images(0, 12, ImageSheet.imageObjects),
            },
            {
                    new Images(1, 11, ImageSheet.imageObjects),
                    new Images(1, 12, ImageSheet.imageObjects),
            },
            {

                    new Images(2, 11, ImageSheet.imageObjects),
                    new Images(2, 12, ImageSheet.imageObjects),
            },
            {
                    new Images(3, 11, ImageSheet.imageObjects),
                    new Images(3, 12, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 11, ImageSheet.imageObjects),
                    new Images(7, 11, ImageSheet.imageObjects),
            },
            {
                    new Images(1, 11, ImageSheet.imageObjects),
                    new Images(7, 11, ImageSheet.imageObjects),
            },
            {

                    new Images(2, 11, ImageSheet.imageObjects),
                    new Images(7, 11, ImageSheet.imageObjects),
            },
            {
                    new Images(3, 11, ImageSheet.imageObjects),
                    new Images(7, 11, ImageSheet.imageObjects)
            }
    };

    public static Images[][] map = {
            {
                    new Images(0, 13, ImageSheet.imageObjects),
                    new Images(1, 13, ImageSheet.imageObjects),
                    new Images(2, 13, ImageSheet.imageObjects),
                    new Images(3, 13, ImageSheet.imageObjects),
                    new Images(4, 13, ImageSheet.imageObjects),
                    new Images(5, 13, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 14, ImageSheet.imageObjects),
                    new Images(1, 14, ImageSheet.imageObjects),
                    new Images(2, 14, ImageSheet.imageObjects),
                    new Images(3, 14, ImageSheet.imageObjects),
                    new Images(4, 14, ImageSheet.imageObjects),
                    new Images(5, 14, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 15, ImageSheet.imageObjects),
                    new Images(1, 15, ImageSheet.imageObjects),
                    new Images(2, 15, ImageSheet.imageObjects),
                    new Images(3, 15, ImageSheet.imageObjects),
                    new Images(4, 15, ImageSheet.imageObjects),
                    new Images(5, 15, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 16, ImageSheet.imageObjects),
                    new Images(1, 16, ImageSheet.imageObjects),
                    new Images(2, 16, ImageSheet.imageObjects),
                    new Images(3, 16, ImageSheet.imageObjects),
                    new Images(4, 16, ImageSheet.imageObjects),
                    new Images(5, 16, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 17, ImageSheet.imageObjects),
                    new Images(1, 17, ImageSheet.imageObjects),
                    new Images(2, 17, ImageSheet.imageObjects),
                    new Images(3, 17, ImageSheet.imageObjects),
                    new Images(4, 17, ImageSheet.imageObjects),
                    new Images(5, 17, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 18, ImageSheet.imageObjects),
                    new Images(1, 18, ImageSheet.imageObjects),
                    new Images(2, 18, ImageSheet.imageObjects),
                    new Images(3, 18, ImageSheet.imageObjects),
                    new Images(4, 18, ImageSheet.imageObjects),
                    new Images(5, 18, ImageSheet.imageObjects)
            },
            {
                    new Images(0, 19, ImageSheet.imageObjects),
                    new Images(1, 19, ImageSheet.imageObjects),
                    new Images(2, 19, ImageSheet.imageObjects),
                    new Images(3, 19, ImageSheet.imageObjects),
                    new Images(4, 19, ImageSheet.imageObjects),
                    new Images(5, 19, ImageSheet.imageObjects)
            },
    };
}
