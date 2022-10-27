# BoomanFX
Project game OOP

Class Diagram: 
  https://app.diagrams.net/?libs=general;uml#Htruongtanthanh1202%2FBoomanFX%2Fmaster%2FBoomanFX.drawio
  
Dev Document:
  https://docs.google.com/document/d/1ZKBAV1EiRL2U4_4aiE_XWMraaGrj-UdJRRQ9BknuDhQ/edit

# Software requirement: 
- JDK 17
- JavaFX
- Intellij IDE(optional)

## Mô tả về các đối tượng trong trò chơi :
- *Bomber* là nhân vật chính của trò chơi. Bomber có thể di chuyển theo 4 hướng trái/phải/lên/xuống theo sự điều khiển của người chơi.
- *Enemy* là các đối tượng mà Bomber phải tiêu diệt hết để có thể qua Level. Enemy có thể di chuyển ngẫu nhiên hoặc tự đuổi theo Bomber tùy theo loại Enemy. Các loại Enemy sẽ được mô tả cụ thể ở phần dưới.
- *Bomb* là đối tượng mà Player đặt và kích hoạt trên các ô Background (không chứa Wall, Box). Một số loại *Enemy* ở level cao cũng có thể đặt Bomb để tiêu diệt Player
- *Background*: là đối tượng "nền" dùng để đặt các đối tượng khác lên (VD: Player, Wall, Box, Portal, ....)
- *Wall*: là đối tượng cố định, không thể phá hủy bằng Bomb cũng như không thể đặt Bomb lên được, Bomber và Enemy không thể di chuyển vào đối tượng này
- *Box*: cũng là đối tượng cố định nhưng có thể phá đươc bằng bomb để nhặt các items chứa sức mạnh 
- *Item*: sẽ xuất hiện ngẫu nhiên khi Box bị phá hủy. Các item khác nhau sẽ increase các chỉ số tương ứng của Player. Item sẽ tự động biến mất sau 1 khoảng thời gian nhất định
  - *HeartItem*: + 1 Live cho Player.
  - *FlameItem*: + 1 độ rộng của explode do bomb gây ra.
  - *BombItem*:  + 1 số bomb được đặt ra liên tiếp của Player.
  - *SpeedItem*: + 1 tốc độ.
- *Explode*: vụ nổ sau khi đối tượng bomb kích hoạt và tạo ra.
- *Portal*: nếu tất cả Enemy đã bị tiêu diệt thì người chơi có thể qua Level khác bằng cách di chuyển vào vị trí của Portal. Số lượng portal sẽ hiện ra ngẫu nhiên khi tất cả enemy bị tiêu diệt.

## Về game play, xử lý các va chạm và bom nổ:
- Trong một màn chơi, Bomber sẽ được người chơi di chuyển (W-A-S-D hoặc dùng phím mũi tên), đặt và kích hoạt Bomb với mục tiêu chính là tiêu diệt tất cả Enemy để qua màn chơi tiếp theo.
- Bomber sẽ bị giết khi va chạm với Enemy hoặc thuộc phạm vi Bomb nổ (Kể cả Bomb do chính Bomber đặt cũng như của Enemy ). Lúc đấy trò chơi kết thúc.
- Enemy bị tiêu diệt khi thuộc phạm vi Bomb nổ
- Một đối tượng thuộc phạm vi Bomb nổ có nghĩa là đối tượng đó va chạm với một trong các tia lửa được tạo ra tại thời điểm một đối tượng Bomb nổ.

## Hệ thống các thuộc tính mỗi level:
- Level 1: Enemy di chuyển ngẫu nhiên, sẽ quay đi tìm đường khác ngay khi vừa gặp bomb. Việc tìm đường khác này là ngẫu nhiên. Khi chạm vào player sẽ khiến player mất 1 mạng. 
- Level 2: Enemy di chuyển ngẫu nhiên nhưng có thể đi xuyên các Box, đặt bomb giết player
- Level 3: Enemy di chuyển đuổi theo Player đặt bomb phá box và giết player, kĩ năng né bom nổ cấp 1 
- Level 4: Enemy di chuyển đuổi theo Player đặt bomb phá box và giết player, kĩ năng né bom nổ cấp 2 
- Level 5: Enemy di chuyển đuổi theo layer đặt bomb phá box và giết player, kĩ năng né bom  cấp 3
