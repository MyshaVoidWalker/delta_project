package net.mvw.delta.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


/**
 * Represents the input box.
 */
public class InputBox implements InputProcessor, GestureDetector.GestureListener {

    /**
     * The screen offset X coordinate.
     */
    public static float xScreenOffset = 0;
    /**
     * The screen offset Y coordinate.
     */
    public static float yScreenOffset = 0;

    public static float masterSoundVolume = 1f, masterMusicVolume = 1f;

    /**
     * Occurs when the player releases the touch.
     *
     * @param screenX The touch X coordinate.
     * @param screenY The touch Y coordinate.
     * @param pointer The pointer.
     * @param button  The button.
     * @return Returs state of action.
     */

    public static boolean updateGameContent = false;

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

//        if (masterMusicVolume < 1) {
//            musicToggle = false;
//        }
//        if (masterSoundVolume < 1) {
//            soundToggle = false;
//        }
//
//        Vector3 touchPos3d = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPos3d);
//        Vector2 touchPos = new Vector2(touchPos3d.x, touchPos3d.y);
//
//
//        if (state.equals(INTRO)) {
//            state = MENU_MAIN;
//            return true;
//        }
//
//        if (state == MENU_CREDITS) {
//            state = MENU_EXTRAS;
//            scrollSetYCredits = 0;
//            return true;
//        }
//
//
//        if (state == MENU_MODS) {
//            for (Mod mod : GameRegistry.mods.values()) {
//                for (int i = uiList.size() - 1; i > uiList.size() - 1 - GameRegistry.mods.size(); i--) {
//                    ScalableEntityUI buttonMod = uiList.get(i);
//                    if (buttonMod.getText().equals(mod.name)) {
//                        if (buttonMod.getSprite().getBoundingRectangle().contains(touchPos) && !GameRegistry.modStates.get(mod.id)) {
//                            String mod_id = "MOD_" + buttonMod.getText().toUpperCase();
//                           updateGameContent = false;
//                            Thread ask = new Thread() {
//                                public void run() {
//                                    /*
//                                    MenuController.addActivationMessage("Attempting purchase...", false);
//                                    if (NetworkManager.validatePurchase(EnvironmentData.population, mod_id)) {
//                                        MenuController.addActivationMessage("Validating purchase...", false);
//
//                                        if (NetworkManager.purchase(EnvironmentData.userID, mod_id)) {
//                                            GameRegistry.activateMod(mod_id);
//
//                                            MenuController.addActivationMessage("Purchase Successful!", false);
//                                            MenuController.addActivationMessage("In case of missing content:", true);
//                                            MenuController.addActivationMessage("Please restart game to ensure content activation!", true);
//                                            updateGameContent = true;
//                                            sound_unlock.play(masterSoundVolume);
//
//                                        } else {
//                                            MenuController.addActivationMessage("Error while trying to purchase!", false);
//                                        }
//
//                                    } else {
//                                        MenuController.addActivationMessage("Not enough humans for purchase", false);
//                                    }*/
//
//                                    MenuController.addActivationMessage("Attempting purchase...", false);
//
//                                    if(EnvironmentData.population > GameRegistry.mods.get(mod_id).price) {
//                                        EnvironmentData.population -= GameRegistry.mods.get(mod_id).price;
//
//                                        GameRegistry.activateMod(mod_id);
//
//                                        MenuController.addActivationMessage("Purchase Successful!", false);
//                                        MenuController.addActivationMessage("In case of missing content:", true);
//                                        MenuController.addActivationMessage("Please restart game to ensure content activation!", true);
//
//                                        updateGameContent = true;
//                                        sound_unlock.play(masterSoundVolume);
//                                    } else {
//                                        MenuController.addActivationMessage("Not enough humans for purchase", false);
//                                    }
//                                }
//                            };
//                            ask.start();
//                        }
//                    }
//                }
//            }
//        }
//
//        if (backButton.getSprite().getBoundingRectangle().contains(touchPos) || backSpriteButton.getBoundingRectangle().contains(touchPos)) {
//            SaveManager.save();
//            switch (state) {
//                case GAME:
//                    if (isObjectSelected) {
//                        isObjectSelected = false;
//                        for (GameObjectUI gameObjectUI : gameObjectUIArrayList) {
//                            gameObjectUI.selected = false;
//                        }
//                    } else {
//                        state = MENU_MAIN;
//                    }
//                    break;
//                case GAME_PROGRESS:
//                    ProgressController.mx = 0;
//                    ProgressController.my = 0;
//                    state = GAME;
//                    break;
//                case GAME_BUILDINGS:
//                    state = GAME;
//                    break;
//                case MENU_MODS:
//                    state = MENU_EXTRAS;
//                    break;
//                default:
//                    state = MENU_MAIN;
//                    break;
//            }
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (playButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            state = GAME;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (extrasButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            state = MENU_EXTRAS;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (optionsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            state = MENU_OPTIONS;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (exitButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            sound_pop.play(masterSoundVolume);
//            SaveManager.save();
//            Gdx.app.exit();
//            return true;
//        }
//
//        if (aboutButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            Gdx.net.openURI("https://spectralbit.com/home");
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (creditsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            state = MENU_CREDITS;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (achievementsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            state = MENU_ACHIEVEMENTS;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (modsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//            StringBuilder loadedMods = new StringBuilder();
//            loadedMods.append("Loaded mods:\n");
//
//            for (Map.Entry<String, Mod> x : GameRegistry.mods.entrySet()) {
//
//                loadedMods.append(Global.JSON.prettyPrint(x.getValue()));
//                loadedMods.append("\n");
//            }
//
//            Global.printDebug(loadedMods.toString(), "InputBox");
//            state = MENU_MODS;
//            return true;
//        }
//
//        if (soundToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
//            soundToggle = false;
//            masterSoundVolume = 0f;
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (soundToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
//            soundToggle = true;
//            masterSoundVolume = 1f;
//            sound_pop.play(masterSoundVolume);
//            return false;
//        }
//
//        if (musicToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
//            musicToggle = false;
//            masterMusicVolume = 0f;
//            playingTrack.setVolume(masterMusicVolume);
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (musicToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
//            musicToggle = true;
//            masterMusicVolume = 1f;
//
//            playingTrack.setVolume(masterMusicVolume);
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (progressSpriteButton.getBoundingRectangle().contains(touchPos)) {
//            state = GAME_PROGRESS;
//            GameController.isObjectSelected = false;
//            for (GameObjectUI gameObjectUI : gameObjectUIArrayList) {
//                gameObjectUI.selected = false;
//            }
//            ProgressController.treeScale = 1;
//            Resources.sound_pop.play(InputBox.masterSoundVolume);
//
//            return true;
//        }
//
//        if (state == GAME_BUILDINGS) {
//            if (touchPos.y > camera.position.y - camera.zoom * SCREEN_HEIGHT / 2 + 512 || touchPos.x > camera.position.x - camera.zoom * SCREEN_WIDTH / 2 - camera.zoom * xScreenOffset + 386) {
//                state = GAME;
//                return true;
//            }
//            if (nextBuildingSprite.getBoundingRectangle().contains(touchPos)) {
//                int kl = indexOfBuildingShown;
//                indexOfBuildingShown = getNextIndex(indexOfBuildingShown);
//                if (indexOfBuildingShown != kl) {
//                    Resources.sound_pop.play(InputBox.masterSoundVolume);
//                }
//                return true;
//            }
//            if (prevBuildingSprite.getBoundingRectangle().contains(touchPos)) {
//                int kl = indexOfBuildingShown;
//                indexOfBuildingShown = getPrevIndex(indexOfBuildingShown);
//                if (indexOfBuildingShown != kl) {
//                    Resources.sound_pop.play(InputBox.masterSoundVolume);
//                }
//                return true;
//            }
//
//
//        }
//
//
//        if (state == GameState.GAME) {
//
//            boolean onSprite = false;
//
//            for (GameObjectUI gameObjectUI : gameObjectUIArrayList) {
//                if (gameObjectUI.current.getBoundingRectangle().contains(touchPos) && !onSprite) {
//                    gameObjectUI.selected = true;
//                    onSprite = true;
//                } else if (buildSpriteButton.getBoundingRectangle().contains(touchPos)) {
//                    state = GAME_BUILDINGS;
//                } else if (recycleSpriteButton.getBoundingRectangle().contains(touchPos) && gameObjectUI.selected == true) {
//                    GameRegistry.materials.get(gameObjectUI.factory.costType).amount += (long) (gameObjectUI.factory.costAmount / 3);
//                    gameObjectUI.factory = null;
//                    gameObjectUI.current.setTexture(Resources.natureTextures.get((int) ((gameObjectUI.surface.equals(GameObjectUI.Surface._LAND) ? 0 : 1) * 6 + (Math.random() * Resources.natureTextures.size() / 2))));
//                } else {
//                    gameObjectUI.selected = false;
//                }
//
//            }
//            sound_pop.play(masterSoundVolume);
//            return true;
//        }
//
//        if (state == GameState.GAME_BUILDINGS) {
//            if (buildOnPlanetSprite.getBoundingRectangle().contains(touchPos)) {
//                Factory factory = new Factory(buildingsList.get(indexOfBuildingShown));
//                if (GameRegistry.materials.get(factory.costType).amount >= factory.costAmount) {
//                    for (GameObjectUI gameObjectUI : gameObjectUIArrayList) {
//                        if (gameObjectUI.selected) {
//                            gameObjectUI.factory = factory;
//                            printDebug(gameObjectUI.factory.id, "BUILDER");
//                            gameObjectUI.current.setTexture(factory.getSprite().getTexture());
//                            state = GAME;
//                            gameObjectUI.selected = false;
//                            sound_build.play(masterSoundVolume);
//                        }
//                    }
//                    GameRegistry.materials.get(factory.costType).amount -= factory.costAmount;
//                }
//
//
//                return true;
//            }
//
//        }
//
//        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
//            if (state == GAME_PROGRESS) {
//                showReqHover = unlockNodes(ProgressController.progressTree.rootNode, touchPos);
//            }
//        }
//
        return false;
    }

//    public boolean unlockNodes(ProgressNode<Factory> rootNode, Vector2 touchPos) {
//
//        boolean returnValue = false;
//        if (rootNode.data.available && rootNode.data.getSprite().getBoundingRectangle().contains(touchPos)) {
//            returnValue = true;
//            reqX = touchPos.x;
//            reqY = touchPos.y;
//            if (!rootNode.data.prerequisiteMaterialType.equals("")) {
//                reqMat = rootNode.data.prerequisiteMaterialType;
//                reqAmount = rootNode.data.prerequisiteMaterialAmount;
//            } else {
//                reqMat = "";
//            }
//            //rootNode.data.unlocked = true;
//        }
//
//        if (rootNode.children != null) {
//            if (rootNode.data.unlocked) {
//                for (ProgressNode<Factory> node : rootNode.children) {
//                    node.data.available = true;
//                    returnValue |= unlockNodes(node, touchPos);
//                }
//            }
//        }
//
//        return returnValue;
//    }

    /**
     * Inputs required for continual action;
     */
    public static void continualInputs() {


    }

    /**
     * Occurs when the player holds down a key.
     *
     * @param keycode The code of the key.
     * @return
     */
    @Override
    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.BACKSPACE || keycode == Input.Keys.BACK) {
//            SaveManager.save();
//            switch (state) {
//                case GAME:
//                    if (isObjectSelected) {
//                        isObjectSelected = false;
//                        for (GameObjectUI gameObjectUI : gameObjectUIArrayList) {
//                            gameObjectUI.selected = false;
//                        }
//                    } else {
//                        state = MENU_MAIN;
//                    }
//                    break;
//                case GAME_PROGRESS:
//                    ProgressController.mx = 0;
//                    ProgressController.my = 0;
//                    state = GAME;
//                    break;
//                case GAME_BUILDINGS:
//                    state = GAME;
//                    break;
//                case MENU_MODS:
//                    state = MENU_EXTRAS;
//                    break;
//                default:
//                    state = MENU_MAIN;
//                    break;
//            }
//            sound_pop.play(masterSoundVolume);
//        }
//
//        if (state == GAME_BUILDINGS) {
//            if (keycode == Input.Keys.RIGHT) {
//                indexOfBuildingShown = getNextIndex(indexOfBuildingShown);
//                return true;
//            }
//            if (keycode == Input.Keys.LEFT) {
//                indexOfBuildingShown = getPrevIndex(indexOfBuildingShown);
//                return true;
//            }
//        }
//        //if (DEBUG_MODE) {
//            if (keycode == Input.Keys.T) {
//                EnvironmentData.temperature++;
//            }
//
//            if (keycode == Input.Keys.E) {
//                EnvironmentData.ecology--;
//            }
//
//            if (keycode == Input.Keys.M) {
//                GameRegistry.materials.get("MATERIAL_MATERIALS").add(1000);
//            }
//
//            if (keycode == Input.Keys.N) {
//                GameRegistry.materials.get("MATERIAL_FUEL").add(1000);
//            }
//
//            if (keycode == Input.Keys.B) {
//                GameRegistry.materials.get("MATERIAL_IONS").add(1000);
//            }
//
//            if (keycode == Input.Keys.V) {
//                GameRegistry.materials.get("MATERIAL_FOOD").add(1000);
//            }
//
//            if (keycode == Input.Keys.C) {
//                GameRegistry.materials.get("MATERIAL_ELECTRICITY").add(1000);
//            }
//        //}
//

        return false;
    }

    /**
     * Sets the screen offset X coordinate.
     */
    public static void setxScreenOffset(float xScreenOffset) {
        InputBox.xScreenOffset = xScreenOffset;
    }

    /**
     * Sets the screen offset Y coordinate.
     */
    public static void setyScreenOffset(float yScreenOffset) {
        InputBox.yScreenOffset = yScreenOffset;
    }

    static Vector2 lastTouch = new Vector2();

    /**
     * Occurs when the player touches.
     *
     * @param screenX The touch X coordinate.
     * @param screenY The touch Y coordinate.
     * @param pointer The pointer.
     * @param button  The button.
     * @return
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastTouch.set(screenX, screenY);
        return false;
    }

    private static float xVelocity, yVelocity;

    /**
     * Occurs when the player drags.
     *
     * @param screenX The touch X coordinate.
     * @param screenY The touch Y coordinate.
     * @param pointer The pointer.
     * @return
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 newTouch = new Vector2(screenX, screenY);
        // delta will now hold the difference between the last and the current touch positions
        // delta.x > 0 means the touch moved to the right, delta.x < 0 means a move to the left
        Vector2 delta = newTouch.cpy().sub(lastTouch);
        xVelocity = delta.x;
        yVelocity = delta.y;
        lastTouch = newTouch;

//        if (state == GAME_PROGRESS) {
//            if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
//                ProgressController.mx += xVelocity;
//                ProgressController.my -= yVelocity;
//            } else {
//                ProgressController.mx += xVelocity;
//                ProgressController.my -= yVelocity;
//            }
//        }
//
//        showReqHover = false;


        return false;
    }

    /**
     * Occurs when the player moves the mouse.
     *
     * @param screenX The touch X coordinate.
     * @param screenY The touch Y coordinate.
     * @return
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Vector3 touchPos3d = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
//        camera.unproject(touchPos3d);
//        Vector2 touchPos = new Vector2(touchPos3d.x, touchPos3d.y);
//
//        if (state == MENU_MAIN) {
//            if (playButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                playButton.hovered = true;
//                optionsButton.hovered = false;
//                extrasButton.hovered = false;
//                exitButton.hovered = false;
//                return true;
//            } else {
//                playButton.hovered = false;
//            }
//
//            if (extrasButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                extrasButton.hovered = true;
//                playButton.hovered = false;
//                optionsButton.hovered = false;
//                exitButton.hovered = false;
//                return true;
//            } else {
//                extrasButton.hovered = false;
//            }
//
//            if (optionsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                optionsButton.hovered = true;
//                exitButton.hovered = false;
//                return true;
//            } else {
//                optionsButton.hovered = false;
//            }
//
//            if (exitButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                exitButton.hovered = true;
//                return true;
//            } else {
//                exitButton.hovered = false;
//            }
//        }
//
//        if (state == MENU_EXTRAS) {
//
//            if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                backButton.hovered = true;
//                aboutButton.hovered = false;
//                return true;
//            } else {
//                backButton.hovered = false;
//            }
//
//            if (achievementsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                achievementsButton.hovered = true;
//                modsButton.hovered = false;
//                return true;
//            } else {
//                achievementsButton.hovered = false;
//            }
//
//            if (modsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                modsButton.hovered = true;
//                achievementsButton.hovered = false;
//                creditsButton.hovered = false;
//                aboutButton.hovered = false;
//                return true;
//            } else {
//                achievementsButton.hovered = false;
//            }
//
//            if (aboutButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                aboutButton.hovered = true;
//                creditsButton.hovered = false;
//                modsButton.hovered = false;
//                return true;
//            } else {
//                aboutButton.hovered = false;
//            }
//
//            if (creditsButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                creditsButton.hovered = true;
//                modsButton.hovered = false;
//                aboutButton.hovered = false;
//                backButton.hovered = false;
//                return true;
//            } else {
//                creditsButton.hovered = false;
//            }
//
//
//        }
//
//        if (state == MENU_OPTIONS) {
//
//            if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                backButton.hovered = true;
//                musicToggleButtonOFF.hovered = false;
//                musicToggleButtonON.hovered = false;
//                return true;
//            } else {
//                backButton.hovered = false;
//            }
//
//            if (soundToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
//                soundToggleButtonON.hovered = true;
//                musicToggleButtonOFF.hovered = false;
//                musicToggleButtonON.hovered = false;
//                return true;
//            } else {
//                soundToggleButtonON.hovered = false;
//            }
//
//            if (soundToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
//                soundToggleButtonOFF.hovered = true;
//                musicToggleButtonOFF.hovered = false;
//                musicToggleButtonON.hovered = false;
//                return true;
//            } else {
//                soundToggleButtonOFF.hovered = false;
//            }
//
//            if (musicToggleButtonON.getSprite().getBoundingRectangle().contains(touchPos)) {
//                musicToggleButtonON.hovered = true;
//                soundToggleButtonON.hovered = false;
//                soundToggleButtonOFF.hovered = false;
//                backButton.hovered = false;
//                return true;
//            } else {
//                musicToggleButtonON.hovered = false;
//            }
//
//            if (musicToggleButtonOFF.getSprite().getBoundingRectangle().contains(touchPos)) {
//                musicToggleButtonOFF.hovered = true;
//                soundToggleButtonON.hovered = false;
//                soundToggleButtonOFF.hovered = false;
//                backButton.hovered = false;
//                return true;
//            } else {
//                musicToggleButtonOFF.hovered = false;
//            }
//
//        }
//
//        if (state == MENU_MODS) {
//            for (Mod mod : GameRegistry.mods.values()) {
//                for (int i = uiList.size() - 1; i > uiList.size() - 1 - GameRegistry.mods.size(); i--) {
//                    ScalableEntityUI buttonMod = uiList.get(i);
//                    if (buttonMod.getText().equals(mod.name)) {
//                        if (buttonMod.getSprite().getBoundingRectangle().contains(touchPos)) {
//                            buttonMod.hovered = true;
//                        } else {
//                            buttonMod.hovered = false;
//                        }
//                    }
//                }
//            }
//            if (backButton.getSprite().getBoundingRectangle().contains(touchPos)) {
//                backButton.hovered = true;
//            } else {
//                backButton.hovered = false;
//            }
//            return true;
//        }
//
//        if (state == GAME_PROGRESS) {
//            showReqHover = unlockNodes(ProgressController.progressTree.rootNode, touchPos);
//        }
//
//        if (state == MENU_ACHIEVEMENTS) {
//            if (mScorch.getBoundingRectangle().contains(touchPos) || mFlood.getBoundingRectangle().contains(touchPos) || mStorm.getBoundingRectangle().contains(touchPos)) {
//                medalHover = true;
//                hX = touchPos.x;
//                hY = touchPos.y;
//                if (mScorch.getBoundingRectangle().contains(touchPos)) {
//                    hMessage = "Obtained by causing THE SCORCH.";
//                }
//                if (mFlood.getBoundingRectangle().contains(touchPos)) {
//                    hMessage = "Obtained by causing THE FLOOD.";
//                }
//                if (mStorm.getBoundingRectangle().contains(touchPos)) {
//                    hMessage = "Obtained by causing THE STORM.";
//                }
//
//            } else {
//                medalHover = false;
//            }
//        } else {
//            medalHover = false;
//        }

        return false;
    }

    /**
     * Occurs when the player scrolls.
     *
     * @param amount How much the player scrolled.
     * @return
     */
    @Override
    public boolean scrolled(int amount) {
//        if (state == GAME_PROGRESS) {
//            if (amount != 0) {
//                if (ProgressController.treeScale >= 0.5f && ProgressController.treeScale <= 4f) {
//                    ProgressController.treeScale -= amount * 4 * getDelta();
//                }
//                if (ProgressController.treeScale < 0.5f) {
//                    ProgressController.treeScale = 0.5f;
//                }
//                if (ProgressController.treeScale > 4f) {
//                    ProgressController.treeScale = 4f;
//                }
//
//                return true;
//            }
//        }
        return false;
    }

    /**
     * Occurs when the player releases a key.
     *
     * @param keycode The code of the key.
     * @return
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Occurs when the player types a character.
     *
     * @param character The character.
     * @return
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        deltaDist = distance - initialDistance;
//        if (state == GAME_PROGRESS) {
//            if (deltaDist != 0) {
//                if (ProgressController.treeScale >= 0.5f && ProgressController.treeScale <= 4f) {
//                    ProgressController.treeScale += deltaDist / 64 * getDelta();
//                }
//                if (ProgressController.treeScale < 0.5f) {
//                    ProgressController.treeScale = 0.5f;
//                }
//                if (ProgressController.treeScale > 4f) {
//                    ProgressController.treeScale = 4f;
//                }
//
//                return true;
//            }
//        }
        return false;
    }

    float deltaDist = 0;

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2
            pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
