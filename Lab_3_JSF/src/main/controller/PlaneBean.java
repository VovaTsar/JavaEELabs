package controller;

import JPA.ManageDataBase;
import JPA.PlaneEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@ManagedBean
public class PlaneBean {
    PlaneEntity planeEntity = new PlaneEntity();
    ManageDataBase manageDataBase = new ManageDataBase();
    private int id;
    private String brand;
    private String captain;
    private String engine;
    private String series;
    private String editPlaneId;

    public void setEditPlaneId(String editPlaneId) {
        this.editPlaneId = editPlaneId;
    }

    public String getEditPlaneId() {
        return editPlaneId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getCaptain() {
        return captain;
    }

    public String getEngine() {
        return engine;
    }

    public String getSeries() {
        return series;
    }

    public PlaneEntity getPlaneEntity() {
        return planeEntity;
    }

    public void setPlaneEntity(PlaneEntity planeEntity) {
        this.planeEntity = planeEntity;
    }

    public void addInfo() {
        if (requestIsValid()) {
            manageDataBase.persistEntity(planeEntity);
        } else {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.setResponseStatus(HttpServletResponse.SC_NOT_FOUND);
            facesContext.responseComplete();
            return;
        }
    }

    public List<PlaneBean> planeListFromDb() {
        return manageDataBase.getAll();
    }

    public String deletePlaneById(int planeId) {
        return manageDataBase.deletePlaneDetails(planeId);
    }

    public String editPlaneDetailsById() {
        editPlaneId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedPlaneId");
        return "planeRefactor.xhtml";
    }

    public String updatePlaneDetails(PlaneBean planeBean) {
        return manageDataBase.updatePlaneDetails(Integer.parseInt(planeBean.getEditPlaneId()), planeBean.getBrand(), planeBean.getCaptain(), planeBean.getEngine(), planeBean.getSeries());
    }

    private boolean requestIsValid() {
        return planeEntity.getBrand() != null && planeEntity.getBrand().length() > 0 &&
                planeEntity.getCaptain() != null && planeEntity.getCaptain().length() > 0 &&
                planeEntity.getEngine() != null && planeEntity.getEngine().length() > 0 &&
                planeEntity.getSeries() != null && planeEntity.getSeries().length() > 0;
    }
}
