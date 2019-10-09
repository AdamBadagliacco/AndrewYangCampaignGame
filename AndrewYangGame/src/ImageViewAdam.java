/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.image.ImageView;

/**
 * This class adds an ID to the imageView object that way I can tell what type of bill was collected so you can lose if you collect one that was too high before collecting the lower amounts
 * @author Alfster
 */
public class ImageViewAdam extends ImageView {
    
    private int id;
    
    public ImageViewAdam(String url, int id){
        super(url);
        this.id = id;
        
    }

    /**
     * @return the id
     */
    public int getAdamID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
