package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            1 -> {
                println("Digite o nome do cachorro:")
                val nome = readLine()
                println("Digite a idade do cachorro:")
                val idade = readLine()?.toIntOrNull() ?: 0
                val cachorro = Cachorro(idade, Cor.preto.toString(), nome ?: "")
                repositorioAnimal.adicionar(cachorro)

            }
            2 -> {
                println("Digite o nome do gato:")
                val nome = readln()
                println("Digite a idade do gato:")
                val idade = readln()?.toIntOrNull() ?: 0
                val gato = Gato(idade, Cor.vermelho.toString(), nome)
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                println("Digite o nome do pássaro:")
                val nome = readln()
                println("Digite a idade do pássaro:")
                val idade = readln()?.toIntOrNull() ?: 0
                val passaro = Passaro(idade, Cor.cinza.toString(), nome)
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom() }
            }
            6 -> {
                println("Digite a cor dos animais a serem listados:")
                var cor = readlnOrNull()
                if (cor != null) {
                    repositorioAnimal.listarPorCor(cor)
                }
            }
            7 -> {
                println("Digite o nome do animal:")
                var nome = readlnOrNull()
                if (nome != null) {
                    repositorioAnimal.remover(nome)
                }
            }
            8 -> {
                println("Digite a idade dos animais a serem listados:")
                var idade = readlnOrNull()?.toIntOrNull()
                if (idade != null) {
                    repositorioAnimal.listarPorIdade(idade)
                } else {
                    println("Idade inválida.")
                }
            }
            9 -> {
                println("Digite o nome da pessoa:")
                val nome = readlnOrNull() ?: ""
                val pessoa = Humano(30, Cor.preto.toString(), nome)
                repositorioAnimal.adicionar(pessoa)
            }
        }
    }
}



abstract class Animal(var idade: Int,var cor:String) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }
    open fun idadeEmAnosHumanos(idade: Int): Int {
        return idade * 7

    }


    abstract fun emitirSom()
}

enum class Cor() {
    rosa,preto,cinza,vermelho
}

class Cachorro(idade: Int, cor: String, nome: String) : Animal(idade, cor) {
    init {
        this.nome = nome
    }
    override fun emitirSom() {
        println("Au au")
    }
}

class Gato(idade: Int, cor: String, nome: String) : Animal(idade, cor) {
    init {
        this.nome = nome
    }
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: String, nome: String) : Animal(idade, cor) {
    init {
        this.nome = nome
    }
    override fun emitirSom() {
        println("Piu piu")
    }
}

class Humano(idade: Int, cor: String, nome: String) : Animal(idade, cor) {
    init {
        this.nome = nome
    }
    override fun idadeEmAnosHumanos(idade: Int): Int {
        return idade
    }
    override fun emitirSom() {
        println("Som de um Humano")
    }
}

fun menu() {
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Emitir Som")
    println("6 - Listar Animais Por Cor")
    println("7 - Remover Animais")
    println("8 - Listar Animais Por Idade")
    println("9 - Criar Humano")
    println("10- sair")



}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }


    fun listarPorCor(cor: String) {
        val animaisFiltradosPorCor = animais.filter { it.cor.equals(cor, ignoreCase = true) }
        if (animaisFiltradosPorCor.isEmpty()) {
            println("Não há animais da cor $cor.")
        } else {
            animaisFiltradosPorCor.forEach { println("${it.nome} , ${it.cor}") }
        }
    }
    fun listar() {
        animais.forEach { animal -> println("${animal.nome} , ${animal.cor}") }

    }


    fun listarPorIdade(idade: Int) {
        val animaisFiltradosPorIdade = animais.filter { it.idade == idade }
        if (animaisFiltradosPorIdade.isEmpty()) {
            println("Não existe animais com a idade.")
        } else {
            animaisFiltradosPorIdade.forEach { println("${it.nome} , ${it.cor}") }
        }
    }

    fun remover(nome: String) {
        var animalASerRemovido = animais.filter { animal -> animal.nome == nome }
        if (animalASerRemovido.isNotEmpty()) {
            animais.remove(animalASerRemovido[0])
        } else {
            println("O animal $nome não foi encontrado na lista.")
        }

        fun BuscarAnimalPeloNome(nome:String): Animal? {
            return animais.find { it.nome == nome }
        }

    }
    }

