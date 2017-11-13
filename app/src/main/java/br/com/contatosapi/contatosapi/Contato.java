package br.com.contatosapi.contatosapi;

/**
 * Created by 16254861 on 13/11/2017.
 */

public class Contato {

    private String nome;
    private String telefone;
    private String foto;

    //Para trabalhar com fotos foi adicionado a biblioteca PICASSO em app/libs

    //Fabrica de Contatos
    public static Contato create(String nome, String telefone, String foto){

        Contato c = new Contato();
        c.setNome(nome);
        c.setTelefone(telefone);
        c.setFoto(foto);
        return c;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
