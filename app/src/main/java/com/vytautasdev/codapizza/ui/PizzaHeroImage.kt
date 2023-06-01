package com.vytautasdev.codapizza.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vytautasdev.codapizza.R
import com.vytautasdev.codapizza.model.Pizza
import com.vytautasdev.codapizza.model.Topping.Basil
import com.vytautasdev.codapizza.model.Topping.Pepperoni
import com.vytautasdev.codapizza.model.Topping.Pineapple
import com.vytautasdev.codapizza.model.ToppingPlacement.All
import com.vytautasdev.codapizza.model.ToppingPlacement.Left
import com.vytautasdev.codapizza.model.ToppingPlacement.Right

@Composable
fun PizzaHeroImage(
    pizza: Pizza,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(R.drawable.pizza_crust),
            contentDescription = stringResource(R.string.pizza_preview),
            modifier = Modifier.fillMaxSize()
        )
        pizza.toppings.forEach { (topping, placement) ->
            Image(
                painter = painterResource(topping.pizzaOverlayImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = when (placement) {
                    Left -> Alignment.TopStart
                    Right -> Alignment.TopEnd
                    All -> Alignment.Center
                },
                modifier = Modifier
                    .focusable(false)
                    .aspectRatio(
                        when (placement) {
                            Left, Right -> 0.5f
                            All -> 1.0f
                        }
                    )
                    .align(
                        when (placement) {
                            Left -> Alignment.CenterStart
                            Right -> Alignment.CenterEnd
                            All -> Alignment.Center
                        }
                    )
            )
        }
    }
}

@Preview
@Composable
private fun PizzaHeroImagePreview() {
    PizzaHeroImage(
        pizza = Pizza(
            toppings = mapOf(
                Pineapple to All, Pepperoni to Left,
                Basil to Right
            )
        )
    )
}