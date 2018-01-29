package com.movil.summmit.motorresapp.Models.Enity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

/**
 * Created by cgonzalez on 16/01/2018.
 */
@DatabaseTable
public class InformeTecnicoFallaCausa {
    @DatabaseField(generatedId = true)
    private int IdFallaCausa;
    @DatabaseField
    private int IdInformeTecnicoFalla;
    @DatabaseField
    private String Descripcion;
    @DatabaseField
    private int AudUsuarioRegistro;
    @DatabaseField
    private Date AudFechaRegistro;

    public int getIdFallaCausa() {
        return IdFallaCausa;
    }

    public void setIdFallaCausa(int idFallaCausa) {
        IdFallaCausa = idFallaCausa;
    }

    public int getIdInformeTecnicoFalla() {
        return IdInformeTecnicoFalla;
    }

    public void setIdInformeTecnicoFalla(int idInformeTecnicoFalla) {
        IdInformeTecnicoFalla = idInformeTecnicoFalla;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getAudUsuarioRegistro() {
        return AudUsuarioRegistro;
    }

    public void setAudUsuarioRegistro(int audUsuarioRegistro) {
        AudUsuarioRegistro = audUsuarioRegistro;
    }

    public Date getAudFechaRegistro() {
        return AudFechaRegistro;
    }

    public void setAudFechaRegistro(Date audFechaRegistro) {
        AudFechaRegistro = audFechaRegistro;
    }
}
