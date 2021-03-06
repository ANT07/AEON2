package aeon.modelo.dto;
// Generated 10-10-2018 06:07:14 PM by Hibernate Tools 4.3.1



/**
 * DetalleVenta generated by hbm2java
 */
public class DetalleVenta  implements java.io.Serializable {


     private int iddetalle;
     private int producto;
     private int venta;
     private double cantidad;
     private double precio;
     private double totaldetalle;
     private String nombreProducto ;

    public DetalleVenta() {
    }

	
    public DetalleVenta(int iddetalle) {
        this.iddetalle = iddetalle;
    }
   
    public int getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public double getTotaldetalle() {
        return this.totaldetalle;
    }
    
    public void setTotaldetalle(double totaldetalle) {
        this.totaldetalle = totaldetalle;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.producto;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetalleVenta other = (DetalleVenta) obj;
        if (this.producto != other.producto) {
            return false;
        }
        other.setCantidad(other.cantidad + this.getCantidad());
        other.setTotaldetalle(other.cantidad* other.getPrecio());
        return true;
    }




}


