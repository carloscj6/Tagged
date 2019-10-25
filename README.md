# Tagged
Simple Tag Layout for displaying tags
This project is inspred by [TagCloudLinkView](https://github.com/namito/TagCloudLinkView) only that this took some
steroids and a can of beer. 
## Screenshots
<img src="/screenshots/with deletion.png" width="300px"> <img src="/screenshots/without deletion.png" width="300px">
## Download
Fancy? get it here
```gradle 

```
## Usage
Quite easy
### Core
First add `TagCloudView` in your Layout as below 
```kotlin
<com.revosleap.tagcloud.ui.TagCloudView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/tagView"/>
 ```
 Whenever your layout is inflated,  Activity/Fragment/Dialog etc, add items to the ``TagCloudView`` as:
 
 ```kotlin
 val tagCloudView = findViewById<TagCloudView>(R.id.tagCloudView)
 ```
 Now Add some tags
 
 ```kotlin
 tagcloudView.add(Tag(1,"Arkansas"))
 ```
 
 With that you are done.
 Let the `tags` flow now.
 ### Click listeners
 To configure Tag onclick listeners, here is what you gonna do.
 when tag is clicked
 
 > Register the OnClickListener 
 
 ```kotlin
 tagView.setOnTagSelectListener(object :TagCloudView.OnTagSelectListener{
            override fun onTagSelected(tag: Tag, position: Int) {

            }
        })
   ```
  To remove the tag and listen to the event, do this;
  
  ```kotlin
  tagView.setOnTagDeleteListener(object:TagCloudView.OnTagDeleteListener{
            override fun onTagDeleted(tag: Tag, position: Int) {

            }
        })
  ```
  ## Customization
   I'm not perfect, so you may want something better. 
   The library gives you alot of power in customization. You can design your own drawable background
   and give it to the view. 
   > Since the libray uses [FlowLayout](https://github.com/nex3z/FlowLayout), all the flowlayout attributes are available
   for you. 
   
   ### Tag Items
   
   The following fields are available for customization
   
| Attribute          | Format                |
|--------------------|-----------------------|
| `tagTextColor`     | `color`               |
| `tagTextSize`      | `int`         |
| `deletableTextColor`| `color`                 |
| `deletableTextSize` | `color`                 |
| `tagBackground`     |`drawable resource id`   |
| `deletableBackground`|`drawable resource id`   |
   
### Layout
   
   You can check [FlowLayout](https://github.com/nex3z/FlowLayout) for all the customizable fields but in summary,
   you got this:
   
   | Attribute        | Format    |
   |------------------|-----------|
   |`flChildSpacing`  | `int`     |
   |`flRowSpacing`    |  `int`    |
        
 And many more others.
 Have fun coding
 
 ## Sample
 
 For the one in the screenshot, This is it
 > layout
 
 ```xml 
 <com.revosleap.tagcloud.ui.TagCloudView
        app:isDeletable="false"
        app:flChildSpacing="4dp"
        app:flRowSpacing="4dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/tagView"/>
 ```
> Activity

```kotlin
val tagView= findViewById<TagCloudView>(R.id.tagView)
        tagView.apply {
            add(Tag(1,"Viper"))
            add(Tag(1,"Venom"))
            add(Tag(1,"Plano"))
            add(Tag(1,"Payin"))
            add(Tag(1,"Valerian"))
            add(Tag(1,"Scooby"))
            add(Tag(1,"Pewin"))
            add(Tag(1,"Drac"))
            add(Tag(1,"Cartoons"))
            add(Tag(1,"America"))
            add(Tag(1,"Beard"))
            add(Tag(1,"Yellow Ghost"))
            add(Tag(1,"Monkey Bar"))
        }
        tagView.setOnTagSelectListener(object :TagCloudView.OnTagSelectListener{
            override fun onTagSelected(tag: Tag, position: Int) {
                Toast.makeText(this@MainActivity ,tag.text,Toast.LENGTH_SHORT).show()
            }
        })

        tagView.setOnTagDeleteListener(object:TagCloudView.OnTagDeleteListener{
            override fun onTagDeleted(tag: Tag, position: Int) {

            }
        })
 ```
 ## Credits
 [FlowLayout](https://github.com/nex3z/FlowLayout)
 
 [TagCloudLinkView](https://github.com/namito/TagCloudLinkView)
 
 License
 ------
      MIT License

    Copyright (c) 2019 Carlos Anyona
    
  Find the copy of the license here [Tag Cloud License](/blob/master/LICENSE)
  ##
  Happy coding :blue_heart:
 
