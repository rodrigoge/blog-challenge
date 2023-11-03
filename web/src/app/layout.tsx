import './globals.css'
import { Outfit } from 'next/font/google'

export const metadata = {
  title: 'Blog Challenge',
  description: 'A challenge from development a minimal blog using Clean Architecture and others skills.',
}

const outfit = Outfit({
  weight: ['400', '700'],
  style: ['normal'],
  subsets: ['latin']
})

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en" className={outfit.className}>
      <body className="bg-slate-100 text-zinc-900 flex  items-center justify-center mt-12">
        {children}
      </body>
    </html>
  )
}
